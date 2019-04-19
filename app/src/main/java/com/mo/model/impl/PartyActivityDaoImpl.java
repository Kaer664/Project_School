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
                List<PartyActivityListBean.PartyActivitiesListBean> list = null;
                Bitmap[] bitmaps = null;
                String json = HttpTools.postJson(context, Address.GET_ALL_PARTY_ACTIVITY, null);
                if (json==null){
                    listener.result(list, bitmaps);
                    return;
                }
                Gson gson = new Gson();
                list = gson.fromJson(json, PartyActivityListBean.class).getPartyActivitiesList();
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
    public void getPartyActivityById(final Context context, final String id, final ActivityListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json = HttpTools.postJson(context, Address.GET_PARTY_ACTIVITY_BY_ID, "id", id);
                PartyActivityBean bean=null;
                Bitmap bitmap=null;
                if (json==null){
                    listener.result(bean,bitmap);
                    return;
                }
                Gson gson = new Gson();
                bean = gson.fromJson(json, PartyActivityBean.class);
                bitmap= HttpTools.getBitmap(context, Address.PIC_URL, bean.getPartyActivitiesList().get(0).getImgUrl());
                listener.result(bean, bitmap);
            }
        }.start();
    }
}
