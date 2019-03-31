package com.mo.model.impl;

import android.content.Context;

import com.mo.bean.BirthActivityBean;
import com.mo.bean.BirthdayMonthBean;
import com.mo.model.BirthDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
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
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        BirthdayMonthBean bean = gson.fromJson(json, BirthdayMonthBean.class);
                        listener.result(bean.getUserList());
                    } else {
                        listener.result(null);
                    }
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
                String json = HttpTools.postJson(context, Address.GET_BRITH_ACTIVITY_BY_ID, "id","all");
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        BirthActivityBean bean = gson.fromJson(json, BirthActivityBean.class);
                        listener.result(bean.getBirthActivitiesList());
                    } else {
                        listener.result(null);
                    }
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
                String json = HttpTools.postJson(context, Address.GET_BRITH_ACTIVITY_BY_ID,"id",id);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        BirthActivityBean bean = gson.fromJson(json, BirthActivityBean.class);
                        listener.result(bean.getBirthActivitiesList());
                    } else {
                        listener.result(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
