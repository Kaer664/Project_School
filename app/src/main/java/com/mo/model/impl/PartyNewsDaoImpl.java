package com.mo.model.impl;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.mo.bean.PartyNewsBean;
import com.mo.bean.PartyNewsListBean;
import com.mo.model.PartyNewsDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 风雨诺 on 2019/3/26.
 * 党务要闻控制层
 */

public class PartyNewsDaoImpl implements PartyNewsDao {

    @Override
    public void getAllPartyNews(final Context context, final LoginOkListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_ALL_NEWS, null);
                if (json==null){
                    listener.result(null);
                    return;
                }
                try {
                    JSONObject object = new JSONObject(json);
                    String msg = object.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        PartyNewsListBean bean = gson.fromJson(json, PartyNewsListBean.class);
                        listener.result(bean.getPartyAffairsNewsList());
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
    public void getPartyNewsById(final Context context, final String value, final LoginOkListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_NEWS_BY_ID, "id", value);
                PartyNewsBean.PartyAffairsNewsBean bean = null;
                Bitmap bitmap = null;
                if (json==null){
                    listener.result(bean,bitmap);
                    return;
                }
                try {
                    JSONObject object = new JSONObject(json);
                    String msg = object.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        bean = gson.fromJson(json, PartyNewsBean.class).getPartyAffairsNews().get(0);
                        bitmap = HttpTools.getBitmap(context, Address.PIC_URL, bean.getImgUrl());
                    }
                    listener.result(bean, bitmap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
