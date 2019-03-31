package com.mo.view;

import com.mo.bean.LearningGardenInfoBean;
import com.mo.bean.LearningGardenListBean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/30.
 * 学习园地视图层接口
 */

public interface ILearningGardenView {
    //显示学习园地列表
    void showLearningGardenList(List<LearningGardenListBean.LearningGardensListBean> list);
    //显示学习园地具体信息
    void showLearningGardenInfo(LearningGardenInfoBean bean);
}
