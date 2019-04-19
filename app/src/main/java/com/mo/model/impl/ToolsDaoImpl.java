package com.mo.model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.mo.bean.UserLoginBean;
import com.mo.model.ToolsDao;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

import static android.content.Context.MODE_PRIVATE;

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
                    if (json==null){
                        listener.result(null);
                        return;
                    }
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
    public void getServerTime(final Context context, final NotifyListener listener) {
        new Thread() {
            @Override
            public void run() {
                String json=HttpTools.postJson(context, Address.GET_TIME, null);
                try {
                    JSONObject object=new JSONObject(json);
                    String msg=object.getString("msg");
                    if ("success".equals(msg)){
                        listener.result(object.getString("ServerSysCurrentDate"));
                    }else{
                        listener.result(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 用户发表评论
     * @param context
     * @param map
     * @param listener
     */
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

    /**
     * 用户反馈
     * @param context
     * @param map
     * @param listener
     */
    @Override
    public void addFeedBack(final Context context, final LinkedHashMap<String, String> map, final AddFeedListener listener){
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
                String json = HttpTools.postJson(context, Address.CHANGE_PWD, map);
                try {
                    JSONObject object = new JSONObject(json);
                    String msg = object.getString("msg");
                    if ("success".equals(msg)) {
                        listener.result(true);
                    } else {
                        listener.result(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static SharedPreferences preferences=null;

    public void saveUserInfo(Context context,String userName,String pwd,UserLoginBean bean){
        if (preferences==null){
            preferences= context.getSharedPreferences("userInfo", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",userName);
        editor.putString("pwd",pwd);
            if (bean!=null){
            editor.putString("userId",bean.getUserID());
            editor.putString("userRealName",bean.getUserRealName());
        }else{
            editor.putString("userId",null);
            editor.putString("userRealName",null);
        }
        editor.commit();
    }
    public SharedPreferences readUserInfo(Context context){
        if (preferences==null){
            preferences= context.getSharedPreferences("userInfo", MODE_PRIVATE);
        }
        return preferences;
    }
}
