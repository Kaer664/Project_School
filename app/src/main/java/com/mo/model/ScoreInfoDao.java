package com.mo.model;

import android.content.Context;

import  com.mo.bean.ScoreRankBean;
import  com.mo.bean.UserScoreBean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 * 获取用户积分信息
 */

public interface ScoreInfoDao {
    void getUserScoreInfo(Context context, String userId, ScoreListener listener);
    void getScoreRank(Context context, ScoreListener listener);
    interface ScoreListener{
        void result(UserScoreBean bean);
        void result(List<ScoreRankBean.AllUserScoreListBean> list);
    }
}
