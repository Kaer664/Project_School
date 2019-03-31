package com.mo.model;

import android.content.Context;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 * 先进人物相关接口
 */

public interface AdvancePersonDao {
    /**
     * 获取所有先进人物
     * @param context 上下文
     * @param listener
     */
    void getAllAdvancePerson(Context context, PersonListener listener);

    /**
     * 通过id获取先进人物
     * @param context
     * @param id 先进人物id
     * @param listener
     */
    void getAdvancePersonById(Context context, String id, PersonListener listener);
    interface PersonListener{
        void result(List list);
    }
}
