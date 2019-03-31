package com.mo.model.impl;

import android.content.Context;

import com.mo.bean.AdvancePersonBean;
import com.mo.model.AdvancePersonDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 风雨诺 on 2019/3/27.
 * AdvancePersonDao具体实现类
 */

public class AdvancePersonDaoImpl implements AdvancePersonDao {
    @Override
    public void getAllAdvancePerson(final Context context, final PersonListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_ADVANCED_PERSRON_BY_ID, "id","all");
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        AdvancePersonBean bean = gson.fromJson(json, AdvancePersonBean.class);
                        listener.result(bean.getAdvancedPersonList());
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
    public void getAdvancePersonById(final Context context, final String id, final PersonListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_ADVANCED_PERSRON_BY_ID, "id",id);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        AdvancePersonBean bean = gson.fromJson(json, AdvancePersonBean.class);
                        listener.result(bean.getAdvancedPersonList());
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
