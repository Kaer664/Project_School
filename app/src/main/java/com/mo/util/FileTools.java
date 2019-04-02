package com.mo.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 风雨诺 on 2019/4/2.
 * 文件工具类
 */

public class FileTools {
    public void sharedPresenter(Context context,String fileName){
        SharedPreferences preferences = context.getSharedPreferences(fileName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
//        editor
    }
}
