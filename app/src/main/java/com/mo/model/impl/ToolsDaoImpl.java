package com.mo.model.impl;

import android.content.Context;

import com.mo.bean.UserLoginBean;
import com.mo.model.ToolsDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

/**
 * Created by 风雨诺 on 2019/3/26.
 * 工具接口具体实现类
 */

public class ToolsDaoImpl implements ToolsDao {
    @Override
    public void login(final Context context, final LinkedHashMap<String, String> map, final ToolsDao.loginListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json= HttpTools.postJson(context, Address.USER_LOGIN, map);
                try {
                    JSONObject object=new JSONObject(json);
                    String msg=object.getString("msg");
                    if ("loginSuccess".equals(msg)){
                        Gson gson=new Gson();
                        UserLoginBean bean=gson.fromJson(json,UserLoginBean.class);
                        listener.result(bean);
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
    public void getRollNotify(final Context context, final NotifyListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json=HttpTools.postJson(context, Address.ROLLING_NOTIFY, null);
                try {
                    JSONObject object=new JSONObject(json);
                    String msg=object.getString("msg");
                    if ("success".equals(msg)){
                        listener.result(object.getString("content"));
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
    public void addReply(final Context context, final LinkedHashMap<String, String> map, final AddReplyListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json=HttpTools.postJson(context, Address.ADD_REPLY, map);
                try {
                    JSONObject object=new JSONObject(json);
                    String msg=object.getString("msg");
                    if ("success".equals(msg)){
                        listener.result(true);
                    }else{
                        listener.result(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void addFeedBack(final Context context, final LinkedHashMap<String, String> map, final AddFeedListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json=HttpTools.postJson(context, Address.ADD_FEEDBACK, map);
                try {
                    JSONObject object=new JSONObject(json);
                    String msg=object.getString("msg");
                    if ("success".equals(msg)){
                        listener.result(true);
                    }else{
                        listener.result(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void changePass(final Context context, final LinkedHashMap<String, String> map, final ChangePassListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json=HttpTools.postJson(context, Address.CHANGE_PWD, map);
                try {
                    JSONObject object=new JSONObject(json);
                    String msg=object.getString("msg");
                    if ("success".equals(msg)){
                        listener.result(true);
                    }else{
                        listener.result(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
