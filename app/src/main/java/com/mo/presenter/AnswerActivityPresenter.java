package com.mo.presenter;

import android.content.Context;

import com.mo.model.AnswerActivityDao;
import com.mo.model.impl.AnswerActivityDaoImpl;
import com.mo.view.IAnswerActivityView;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/28.
 */

public class AnswerActivityPresenter {
    private Context context;
    private AnswerActivityDao dao = (AnswerActivityDao) new AnswerActivityDaoImpl();
    private IAnswerActivityView view;

    public AnswerActivityPresenter(Context context, IAnswerActivityView view) {
        this.context = context;
        this.view = view;
    }

    public void getActivityList(String userId) {
        if (dao != null & context != null) {
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
     * 上传成绩,上传完成会直接更新UI，不需要返回值
     * 不需要返回值！！！！
     * @param answerId 问题id
     * @param answerTitle 问题标题
     * @param score 获得分数
     */
    public void saveScore(String answerId, String answerTitle, String score) {
        if (dao != null & context != null) {
            dao.updateScore(context, answerId, answerTitle, score, new AnswerActivityDao.UpdateScoreListener() {
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
