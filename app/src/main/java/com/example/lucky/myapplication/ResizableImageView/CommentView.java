package com.example.lucky.myapplication.ResizableImageView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lucky.myapplication.R;

import java.util.Map;

/**
 * Created by Lucky on 2019/4/9.
 */

public class CommentView extends LinearLayout{
    public CommentView(Context context) {
        super(context);
    }

    private void init(Context context,Map<String,Object> map) {
        View v=LayoutInflater.from(context).inflate(R.layout.details_item,null);
        int img= (int) map.get("headImg");
        String name= (String) map.get("name");
        String content= (String)map.get("content");
        String date= (String)map.get("date");
        ImageView imgHeadImg= (ImageView) v.findViewById(R.id.itemHeadImg);
        TextView itemName= (TextView) v.findViewById(R.id.itemName);
        TextView itemMessage= (TextView) v.findViewById(R.id.itemMessage);
        TextView itemDate= (TextView) v.findViewById(R.id.itemDate);
        imgHeadImg.setImageResource(img);
        itemName.setText(name);
        itemMessage.setText(content);
        itemDate.setText(date);
        this.addView(v);
    }

    public CommentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CommentView(Context context, Map<String,Object> map) {
        super(context);
        init(context,map);
    }

    public CommentView(Context context, @Nullable AttributeSet attrs,Map<String,Object> map) {
        super(context, attrs);
        init(context,map);
    }

    public CommentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,Map<String,Object> map) {
        super(context, attrs, defStyleAttr);
        init(context,map);
    }
}
