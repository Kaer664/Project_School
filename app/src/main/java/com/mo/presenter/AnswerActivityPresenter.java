package com.mo.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mo.model.AnswerActivityDao;
import com.mo.model.ToolsDao;
import com.mo.model.impl.AnswerActivityDaoImpl;
import com.mo.model.impl.ToolsDaoImpl;
import com.mo.view.IAnswerActivityView;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/28.
 */

public class AnswerActivityPresenter {
    private Context context;
    private AnswerActivityDao dao = (AnswerActivityDao) new AnswerActivityDaoImpl();
    private ToolsDao toolsDao = new ToolsDaoImpl();
    private IAnswerActivityView view;

    public AnswerActivityPresenter(Context context, IAnswerActivityView view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 自动从文件读取用户id，不需要程序传入
     */
    public void getActivityList() {
        if (dao != null & context != null) {
            SharedPreferences preferences = toolsDao.readUserInfo(context);
            String userId = preferences.getString("userId", "");
            if (userId==""){
                Log.i("test", "文件中没有用户id或用户名称");
                return;
            }
            dao.getActivtyList(context, userId, new AnswerActivityDao.AnswerListener() {
                @Override
                public void result(List list) {
                    if (view != null) {
                        view.showAnswerActivityList(list);
                    }
                }
            });
        }
    }

    public void getQuestionInfo(String id) {
        if (dao != null & context != null) {
            dao.getQuestionInfo(context, id, new AnswerActivityDao.AnswerListener() {
                @Override
                public void result(List list) {
                    if (view != null) {
                        view.showQuestionInfo(list);
                    }
                }
            });
        }
    }

    /**
     * 上传成绩
     * 不需要返回值！！！！
     * @param answerId    问题id
     * @param answerTitle 问题标题
     * @param score       获得分数
     */
    public void saveScore(String answerId, String answerTitle, String score) {
        if (dao != null & context != null) {
            SharedPreferences preferences = toolsDao.readUserInfo(context);
            String userid = preferences.getString("userId", "");
            String username = preferences.getString("userRealName", "");
            if (userid==""||username==""){
                Log.i("test", "文件中没有用户id或用户名称");
                return;
            }

            dao.updateScore(context, userid, username, answerId, answerTitle, score, new AnswerActivityDao.UpdateScoreListener() {
                @Override
                public void result(Boolean b) {
                    if (view != null) {
                        view.isSave(b);
                    }
                }
            });
        }
    }
}
