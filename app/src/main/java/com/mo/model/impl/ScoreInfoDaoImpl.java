package com.mo.model.impl;

import android.content.Context;

import com.mo.bean.ScoreRankBean;
import com.mo.bean.UserScoreBean;
import com.mo.model.ScoreInfoDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

/**
 * Created by 风雨诺 on 2019/3/29.
 */

public class ScoreInfoDaoImpl implements ScoreInfoDao {

    @Override
    public void getUserScoreInfo(final Context context, final String userId, final ScoreListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_USER_SCORE, "userId", userId);
                Gson gson = new Gson();
                UserScoreBean bean = gson.fromJson(json, UserScoreBean.class);
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
                Gson gson = new Gson();
                ScoreRankBean bean = gson.fromJson(json, ScoreRankBean.class);
                listener.result(bean.getAllUserScoreList());
            }
        }.start();
    }
}
