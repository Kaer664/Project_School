package com.example.lucky.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 15632 on 2019/4/1.
 */

public class PingLunItem extends LinearLayout {
  private   TextView tvPinglunUserName,tvPinglunContext,tvPinglunTime;
    public PingLunItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.pinglunitem,this);
         tvPinglunUserName= (TextView) findViewById(R.id.tvPinglunUserName);
         tvPinglunContext= (TextView) findViewById(R.id.tvPinglunContext);
         tvPinglunTime= (TextView) findViewById(R.id.tvPinglunTime);
    }
}
