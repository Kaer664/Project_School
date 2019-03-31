package com.mo.model.impl;

import android.content.Context;

import com.mo.bean.PartyNewsBean;
import com.mo.bean.PartyNewsListBean;
import com.mo.model.PartyNewsDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 风雨诺 on 2019/3/26.
 */

public class PartyNewsDaoImpl implements PartyNewsDao {

    @Override
    public void getAllPartyNews(final Context context, final LoginOkListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json= HttpTools.postJson(context, Address.GET_ALL_NEWS, null);
                try {
                    JSONObject object=new JSONObject(json);
                    String msg=object.getString("msg");
                    if ("success".equals(msg)){
                        Gson gson=new Gson();
                        PartyNewsListBean bean = gson.fromJson(json, PartyNewsListBean.class);
                        listener.result(bean.getPartyAffairsNewsList());
                    }else{
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
                String json=HttpTools.postJson(context, Address.GET_NEWS_BY_ID, "id",value);
                try {
                    JSONObject object=new JSONObject(json);
                    String msg=object.getString("msg");
                    if ("success".equals(msg)){
                        Gson gson=new Gson();
                        PartyNewsBean bean = gson.fromJson(json, PartyNewsBean.class);
                        listener.result(bean.getPartyAffairsNews());
                    }else{
                        listener.result(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
