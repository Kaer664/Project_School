package com.mo.model.impl;

import android.content.Context;
import android.graphics.Bitmap;

import com.mo.bean.AdvancePersonBean;
import com.mo.model.AdvancePersonDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
                String json = HttpTools.postJson(context, Address.GET_ADVANCED_PERSRON_BY_ID, "id", "all");
                List<AdvancePersonBean.AdvancedPersonListBean> list = null;
                Bitmap[] bitmaps = null;
                if (json==null){
                    listener.result(list, bitmaps);
                    return;
                }
                Gson gson = new Gson();
                list = gson.fromJson(json, AdvancePersonBean.class).getAdvancedPersonList();
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
    public void getAdvancePersonById(final Context context, final String id, final PersonListener listener) {
        new Thread() {
            @Override
            public void run() {
                AdvancePersonBean.AdvancedPersonListBean bean = null;
                Bitmap bitmap = null;
                String json = HttpTools.postJson(context, Address.GET_ADVANCED_PERSRON_BY_ID, "id", id);
                if (json==null){
                    listener.result(bean, bitmap);
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String msg = jsonObject.getString("msg");
                    if ("success".equals(msg)) {
                        Gson gson = new Gson();
                        bean = gson.fromJson(json, AdvancePersonBean.class).getAdvancedPersonList().get(0);
                        bitmap = HttpTools.getBitmap(context, Address.PIC_URL, bean.getImgUrl());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.result(bean, bitmap);
            }
        }.start();
    }
}
