package com.mo.view;

import android.graphics.Bitmap;

import com.mo.bean.PartyNewsBean;
import com.mo.bean.PartyNewsListBean;

import java.util.List;
/**
 * Created by 风雨诺 on 2019/3/30.
 * 党务要闻视图层接口
 */

public interface IPartyNewsView {
    //显示所有党务要闻
    void showAllPartyNews(List<PartyNewsListBean.PartyAffairsNewsListBean> list);
    /**
     * 显示党务要闻具体
     * @param bean 党务要闻具体信息
     * @param bitmap 党务要闻图片
     */
    void showPartyNewsInfo(PartyNewsBean.PartyAffairsNewsBean bean, Bitmap bitmap);
}
