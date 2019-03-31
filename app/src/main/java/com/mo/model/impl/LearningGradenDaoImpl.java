package com.mo.model.impl;

import android.content.Context;

import com.mo.bean.LearningGardenInfoBean;
import com.mo.bean.LearningGardenListBean;
import com.mo.model.LearningGardenDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 * 学习园地模型层实现类
 */

public class LearningGradenDaoImpl implements LearningGardenDao {
    @Override
    public void getAllLearningGarden(final Context context, final LearningGardenDao.LearningGardenListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_LEARNING_GARDEN_BY_ID, "id", "all");
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    List list=null;
                    if ("success".equals(jsonObject.get("msg"))) {
                        Gson gson = new Gson();
                        LearningGardenListBean bean = gson.fromJson(json, LearningGardenListBean.class);
                        list=bean.getLearningGardenList();
                        listener.result(list);
                    }else{
                        listener.result(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void getLearningGardenById(final Context context, final String id, final LearningGardenListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_LEARNING_GARDEN_BY_ID, "id", id);
                Gson gson = new Gson();
                LearningGardenInfoBean bean = gson.fromJson(json, LearningGardenInfoBean.class);
                listener.result(bean);
            }
        }.start();
    }
}
