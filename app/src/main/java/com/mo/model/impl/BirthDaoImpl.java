package com.mo.model.impl;

import android.content.Context;
import android.graphics.Bitmap;

import com.mo.bean.BirthActivityBean;
import com.mo.bean.BirthdayMonthBean;
import com.mo.model.BirthDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 */

public class BirthDaoImpl implements BirthDao {

    @Override
    public void getBirthMonth(final Context context, final BirthDao.BirthListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_BIRTHDAY, null);
                try {
                    List<BirthdayMonthBean.UserListBean> list = null;
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        list = gson.fromJson(json, BirthdayMonthBean.class).getUserList();
                    }
                    listener.result(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void getAllBirthActivity(final Context context, final BirthListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_BRITH_ACTIVITY_BY_ID, "id", "all");
                try {
                    List<BirthActivityBean.BirthActivitiesListBean> list = null;
                    Bitmap[] bitmaps = null;
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        list = gson.fromJson(json, BirthActivityBean.class).getBirthActivitiesList();
                        bitmaps = new Bitmap[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            Bitmap bitmap = HttpTools.getBitmap(context, Address.PIC_URL, list.get(i).getImgUrl());
                            bitmaps[i] = bitmap;
                        }
                    }
                    listener.result(list, bitmaps);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void getBirthActivityById(final Context context, final String id, final BirthListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_BRITH_ACTIVITY_BY_ID, "id", id);
                try {
                    BirthActivityBean.BirthActivitiesListBean bean = null;
                    Bitmap bitmap = null;
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        bean = gson.fromJson(json, BirthActivityBean.class).getBirthActivitiesList().get(0);
                        bitmap=HttpTools.getBitmap(context, Address.PIC_URL, bean.getImgUrl());
                    }
                    listener.result(bean,bitmap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
