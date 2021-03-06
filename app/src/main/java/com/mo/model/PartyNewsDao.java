package com.mo.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.mo.bean.PartyNewsBean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/26.
 * 党务要闻接口
 */

public interface PartyNewsDao {
    void getAllPartyNews(Context context, LoginOkListener listener);
    void getPartyNewsById(Context context, String value, LoginOkListener listener);
    interface LoginOkListener{
        void result(List list);
        void result(PartyNewsBean.PartyAffairsNewsBean bean, Bitmap bitmap);
    }
}
