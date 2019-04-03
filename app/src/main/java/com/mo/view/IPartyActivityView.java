package com.mo.view;

import android.graphics.Bitmap;

import com.mo.bean.PartyActivityBean;
import com.mo.bean.PartyActivityListBean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/30.
 * 党务活动视图层接口
 */

public interface IPartyActivityView {
    //显示党务活动列表
    void showAllPartyActivity(List<PartyActivityListBean.PartyActivitiesListBean> list, Bitmap[] bitmaps);
    //显示党务活动具体信息
    void showPartyActivityInfo(PartyActivityBean bean,Bitmap bitmap);
}
