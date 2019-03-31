package com.mo.model;

import android.content.Context;

import com.mo.bean.LearningGardenInfoBean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 */

public interface LearningGardenDao {
    /**
     * 获取所有学习园地列表
     * @param context 上下文
     * @param listener
     */
    void getAllLearningGarden(Context context, LearningGardenListener listener);

    /**
     * 通过id获取学习园地信息
     * @param context
     * @param id 学习园地id
     * @param listener
     */
    void getLearningGardenById(Context context, String id, LearningGardenListener listener);
    interface LearningGardenListener{
        void result(List list);
        void result(LearningGardenInfoBean infoBean);
    }
}
