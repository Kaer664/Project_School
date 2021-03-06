package com.mo.model.impl;

import android.content.Context;
import android.graphics.Bitmap;

import com.mo.bean.PartyActivityBean;
import com.mo.bean.PartyActivityListBean;
import com.mo.model.PartyActivityDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
                    List<PartyActivityListBean.PartyActivitiesListBean> list = null;
                    Bitmap[] bitmaps = null;
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        list = gson.fromJson(json, PartyActivityListBean.class).getPartyActivitiesList();
                        bitmaps = new Bitmap[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            Bitmap bitmap = HttpTools.getBitmap(context, Address.PIC_URL, list.get(i).getImgUrl());
                            bitmaps[i] = bitmap;
                        }
//                        listener.result(list);
                    }
                    listener.result(list, bitmaps);
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
                Bitmap bitmap = HttpTools.getBitmap(context, Address.PIC_URL, bean.getPartyActivitiesList().get(0).getImgUrl());
                listener.result(bean,bitmap);
            }
        }.start();
    }
}
