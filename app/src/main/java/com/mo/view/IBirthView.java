package com.mo.view;

import android.graphics.Bitmap;

import com.mo.bean.BirthActivityBean;
import com.mo.bean.BirthdayMonthBean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 * 党员生日视图层接口
 */

public interface IBirthView {
    //本月过生日的党员名单
    void showBirthMonth(List<BirthdayMonthBean.UserListBean> list);
    //显示所有生日活动列表
    void showBirthActivityList(List<BirthActivityBean.BirthActivitiesListBean> list, Bitmap[] bitmaps);
    //根据id显示具体生日活动列表
    void showBirthActivityInfo(BirthActivityBean bean, Bitmap bitmap);
}
