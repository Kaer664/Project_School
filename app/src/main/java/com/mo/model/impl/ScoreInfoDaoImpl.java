package com.mo.model.impl;

import android.content.Context;

import com.mo.bean.ScoreRankBean;
import com.mo.bean.UserScoreBean;
import com.mo.model.ScoreInfoDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 */

public class ScoreInfoDaoImpl implements ScoreInfoDao {

    @Override
    public void getUserScoreInfo(final Context context, final String userId, final ScoreListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_USER_SCORE, "userID", userId);
                UserScoreBean bean=null;
                if (json==null){
                    listener.result(bean);
                    return;
                }
                Gson gson = new Gson();
                bean = gson.fromJson(json, UserScoreBean.class);
                listener.result(bean);
            }
        }.start();
    }

    @Override
    public void getScoreRank(final Context context, final ScoreListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_SCORE_RANKING, null);
                List<ScoreRankBean.AllUserScoreListBean> bean=null;
                if (json==null){
                    listener.result(bean);
                    return;
                }
                Gson gson = new Gson();
                bean = gson.fromJson(json, ScoreRankBean.class).getAllUserScoreList();
                listener.result(bean);
            }
        }.start();
    }
}
