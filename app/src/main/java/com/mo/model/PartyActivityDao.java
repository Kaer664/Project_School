package com.mo.model;

import android.content.Context;
import android.graphics.Bitmap;

import  com.mo.bean.PartyActivityBean;

import java.util.List;
/**
 * Created by 风雨诺 on 2019/3/27.
 * 党务活动相关接口
 */

public interface PartyActivityDao {
    /**
     * 获取所有党务活动列表
     * @param context 上下文
     * @param listener 回调
     */
    void getAllPartyActivity(Context context, ActivityListListener listener);

    /**
     * 党务活动列表加载完成调用
     * 返回党务活动列表
     */
    interface ActivityListListener{
        void result(List list, Bitmap[] bitmaps);
    }

    /**
     * 通过id获取党务活动，以及党务活动的评论
     * @param context 上下文
     * @param id    党务活动id
     * @param listener  回调
     */
    void getPartyActivityById(Context context, String id, ActivityListener listener);
    interface ActivityListener{
        void result(PartyActivityBean bean,Bitmap bitmap);
    }
}
