package com.mo.view;

import android.graphics.Bitmap;

import com.mo.bean.LearningGardenInfoBean;
import com.mo.bean.LearningGardenListBean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/30.
 * 学习园地视图层接口
 */

public interface ILearningGardenView {

    /**
     * 显示学习园地列表
     * @param list
     * @param bitmaps
     */
    void showLearningGardenList(List<LearningGardenListBean.LearningGardensListBean> list,Bitmap[] bitmaps);

    /**
     * 显示学习园地具体信息
     * @param bean
     * @param bitmap
     */
    void showLearningGardenInfo(LearningGardenInfoBean bean, Bitmap bitmap);
}
