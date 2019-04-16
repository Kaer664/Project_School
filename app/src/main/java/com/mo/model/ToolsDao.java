package com.mo.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.mo.bean.UserLoginBean;

import java.util.LinkedHashMap;

/**
 * Created by 风雨诺 on 2019/3/26.
 * 工具接口
 * 包含：
 * 用户登陆操作
 * 获取即使滚动通知
 * 发表评论接口
 * 问题反馈
 * 修改密码接口
 */

public interface ToolsDao {
    /**
     * 用户登陆操作
     *
     * @param context  上下文
     * @param map      键值对
     * @param listener 监听
     */
    void login(Context context, LinkedHashMap<String, String> map, loginListener listener);

    interface loginListener {
        void result(UserLoginBean bean);
    }

    /**
     * 获取即时滚动通知
     *
     * @param context
     * @param listener
     */
    void getRollNotify(Context context, NotifyListener listener);

    void getServerTime(Context context, NotifyListener listeners);

    interface NotifyListener {
        void result(String content);
    }

    /**
     * 发表评论
     *
     * @param context
     * @param map
     */
    void addReply(Context context, LinkedHashMap<String, String> map, AddReplyListener listener);

    interface AddReplyListener {
        void result(boolean isAdd);
    }

    /**
     * 问题反馈
     *
     * @param context
     * @param map
     */
    void addFeedBack(Context context, LinkedHashMap<String, String> map, AddFeedListener listener);

    interface AddFeedListener {
        void result(boolean isAdd);
    }

    /**
     * 修改密码接口
     *
     * @param context
     * @param map
     */
    void changePass(Context context, LinkedHashMap<String, String> map, ChangePassListener listener);

    interface ChangePassListener {
        void result(boolean isChange);
    }

    void saveUserInfo(Context context, String userName, String pwd, UserLoginBean bean);

    SharedPreferences readUserInfo(Context context);
}
