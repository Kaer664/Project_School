package com.mo.presenter;

import android.content.Context;

import com.mo.bean.ScoreRankBean;
import com.mo.bean.UserScoreBean;
import com.mo.model.ScoreInfoDao;
import com.mo.model.impl.ScoreInfoDaoImpl;
import com.mo.view.IScoreView;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 */

public class ScorePresenter {
    private Context context;
    private ScoreInfoDao dao= (ScoreInfoDao) new ScoreInfoDaoImpl();
    private IScoreView view;

    public ScorePresenter(Context context, IScoreView view) {
        this.context = context;
        this.view = view;
    }

    /**
     *通过userId获取用户积分信息
     * @param userId 用户id
     */
    public void getUserScoreInfo(String userId){
        if (context!=null&dao!=null){
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
