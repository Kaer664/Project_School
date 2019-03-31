package com.mo.model.impl;

import android.content.Context;

import com.mo.bean.PartyActivityBean;
import com.mo.bean.PartyActivityListBean;
import com.mo.model.PartyActivityDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 风雨诺 on 2019/3/27.
 */

public class PartyActivityDaoImpl implements PartyActivityDao {
    @Override
    public void getAllPartyActivity(final Context context, final PartyActivityDao.ActivityListListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_ALL_PARTY_ACTIVITY, null);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        PartyActivityListBean listBean = gson.fromJson(json, PartyActivityListBean.class);
                        listener.result(listBean.getPartyActivitiesList());
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
    public void getPartyActivityById(final Context context, final String id, final ActivityListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_PARTY_ACTIVITY_BY_ID, "id", id);
                Gson gson = new Gson();
                PartyActivityBean bean = gson.fromJson(json, PartyActivityBean.class);
                listener.result(bean);
            }
        }.start();
    }
}
