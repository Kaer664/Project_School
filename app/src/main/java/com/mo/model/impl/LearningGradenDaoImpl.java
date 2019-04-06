package com.mo.model.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

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
                List<LearningGardenListBean.LearningGardensListBean> list = null;
                Bitmap[] bitmaps = null;
                Gson gson = new Gson();
                list = gson.fromJson(json, LearningGardenListBean.class).getLearningGardenList();
                bitmaps = new Bitmap[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    Bitmap bitmap = HttpTools.getBitmap(context, Address.PIC_URL, list.get(i).getImgUrl());
                    bitmaps[i] = bitmap;
                }
                listener.result(list, bitmaps);
            }
        }.start();
    }

    @Override
    public void getLearningGardenById(final Context context, final String id, final LearningGardenListener listener) {
        new Thread() {
            @Override
            public void run() {
                LearningGardenInfoBean bean = null;
                Bitmap bitmap = null;
                String json = HttpTools.postJson(context, Address.GET_LEARNING_GARDEN_BY_ID, "id", id);
                Gson gson = new Gson();
                bean = gson.fromJson(json, LearningGardenInfoBean.class);
                List<LearningGardenInfoBean.LearningGardenListBean> learningGardenList = bean.getLearningGardenList();
                if (learningGardenList.size() != 0) {
                    bitmap = HttpTools.getBitmap(context, Address.PIC_URL,learningGardenList.get(0).getImgUrl());
                }else{
                    Log.i("test", "run: 没有此id号对应的活动");
                }
                listener.result(bean, bitmap);
            }
        }.start();
    }
}
