package com.mo.model;

import android.content.Context;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 */

public interface BirthDao {
    void getBirthMonth(Context context, BirthListener listener);
    void getAllBirthActivity(Context context, BirthListener listener);
    void getBirthActivityById(Context context, String id, BirthListener listener);
    interface BirthListener{
        void result(List list);
    }
}
