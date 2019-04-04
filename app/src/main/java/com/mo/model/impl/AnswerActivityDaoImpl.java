package com.mo.model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.mo.bean.AnswerActivityListBean;
import com.mo.bean.QuestionInfoBean;
import com.mo.model.AnswerActivityDao;
import com.mo.presenter.ToolsPresenter;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/28.
 */

public class AnswerActivityDaoImpl implements AnswerActivityDao {
    @Override
    public void getActivtyList(final Context context, final String userId, final AnswerListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_ALL_ANSWER_ACTIVITY, "userId", userId);
                try {
                    List<AnswerActivityListBean.UserAnswerActivityListBean> bean = null;
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        bean = gson.fromJson(json, AnswerActivityListBean.class).getUserAnswerActivityList();
                    }
                    listener.result(bean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void getQuestionInfo(final Context context, final String id, final AnswerListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_ANSWER_ACTIVITY_INFO_BY_ID, "id", id);
                try {
                    List<QuestionInfoBean.ProblemListBean> bean = null;
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        bean = gson.fromJson(json, QuestionInfoBean.class).getProblemList();
                    }
                    listener.result(bean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void updateScore(final Context context,String userid,String username, String answerId, String answerTitle, String score, final UpdateScoreListener listener) {
        final LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //这两个从文件获得
        map.put("userID", userid);
        map.put("userName", username);
        map.put("answerActivityID", answerId);
        map.put("answerActivityTitle", answerTitle);
        map.put("score", score);
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.SAVE_SCORE, map);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        listener.result(true);
                    }else{
                        listener.result(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
