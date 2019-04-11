package com.example.lucky.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lucky.myapplication.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 风雨诺 on 2019/4/10.
 */

public class MyAdapter<T extends Map> extends BaseAdapter {
    private List<T> list;
    private Context context;

    public MyAdapter(List<T> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        LayoutInflater inflater=LayoutInflater.from(context);
        if(convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.bitth_item,null);
            holder.imgView= (ImageView) convertView.findViewById(R.id.item_img);
            holder.birthTvName= (TextView) convertView.findViewById(R.id.birthTvName);
            holder.birthTvMsg= (TextView) convertView.findViewById(R.id.birthTvMsg);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        T t = list.get(position);
        holder.birthTvMsg.setText((String) t.get("date"));
        holder.birthTvName.setText((String) t.get("title"));
        holder.imgView.setImageBitmap((Bitmap) t.get("bitmap"));
        return convertView;
    }
    class ViewHolder {
        public ImageView imgView;
        public TextView birthTvName;
        public TextView birthTvMsg;
    }
}
