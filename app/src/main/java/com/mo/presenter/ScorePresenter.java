package com.mo.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mo.bean.ScoreRankBean;
import com.mo.bean.UserScoreBean;
import com.mo.model.ScoreInfoDao;
import com.mo.model.ToolsDao;
import com.mo.model.impl.ScoreInfoDaoImpl;
import com.mo.model.impl.ToolsDaoImpl;
import com.mo.view.IScoreView;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 */

public class ScorePresenter {
    private Context context;
    private ScoreInfoDao dao= new ScoreInfoDaoImpl();
    private ToolsDao toolsDao=new ToolsDaoImpl();
    private IScoreView view;

    public ScorePresenter(Context context, IScoreView view) {
        this.context = context;
        this.view = view;
    }

    /**
     *通过userId获取用户积分信息
     * userId从文件获取
     */
    public void getUserScoreInfo(){
        if (context!=null&dao!=null){
            SharedPreferences preferences = toolsDao.readUserInfo(context);
            String userId = preferences.getString("userId", "");
            if (userId==""){
                Log.i("test", "文件中没有用户id或用户名称");
                return;
            }
            dao.getUserScoreInfo(context, userId, new ScoreInfoDao.ScoreListener() {
                @Override
                public void result(UserScoreBean bean) {
                    if(view!=null){
                        view.showUserScoreInfo(bean);
                    }
                }

                @Override
                public void result(List<ScoreRankBean.AllUserScoreListBean> list) {

                }
            });
        }
    }

    /**
     * 获取积分排行榜
     */
    public void getScoreRank(){
        if (context!=null&&dao!=null){
            dao.getScoreRank(context, new ScoreInfoDao.ScoreListener() {
                @Override
                public void result(UserScoreBean bean) {

                }

                @Override
                public void result(List<ScoreRankBean.AllUserScoreListBean> list) {
                    if (view !=null){
                        view.showScoreRank(list);
                    }
                }
            });
        }
    }
}
