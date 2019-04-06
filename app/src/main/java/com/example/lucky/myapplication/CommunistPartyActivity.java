package com.example.lucky.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mo.bean.PartyActivityBean;
import com.mo.bean.PartyActivityListBean;
import com.mo.presenter.PartyActivityPresenter;
import com.mo.view.IPartyActivityView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 党务活动
 */
public class CommunistPartyActivity extends AppCompatActivity implements IPartyActivityView,AdapterView.OnItemClickListener{

    private ListView partListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communist_party);
        init();
        PartyActivityPresenter partyActivityPresenter = new PartyActivityPresenter(this, this);
        partyActivityPresenter.getAllPartyActivity();
    }

    private void init() {
        partListView= (ListView) findViewById(R.id.partListView);
        //setListView();
    }

    List<Map<String,Object>> data=new ArrayList<>();

    /**
     * 这个方法设置适配器及监听事件
     */
    private void setListView() {
//        SimpleAdapter adapter=new SimpleAdapter(this,data,
//                R.layout.part_item,new String[]{"img","title","date"},new int[]{R.id.park_img,R.id.park_title,R.id.park_data});

        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder=null;
                LayoutInflater inflater=LayoutInflater.from(CommunistPartyActivity.this);
                if(convertView==null){
                    holder=new ViewHolder();
                    convertView=inflater.inflate(R.layout.part_item,null);
                    holder.title= (TextView) convertView.findViewById(R.id.park_title);
                    //holder.title.setMovementMethod(ScrollingMovementMethod.getInstance());  //设置textView可以滚动
                    holder.imageView= (ImageView) convertView.findViewById(R.id.park_img);
                    holder.date= (TextView) convertView.findViewById(R.id.park_data);
                    Map<String,Object> mapHolder=new HashMap<>();
                    mapHolder.put("holder",holder);
                    mapHolder.put("id",holder);
                    convertView.setTag(mapHolder);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String id=(String) data.get(position).get("id");
                            Log.i("TestNUm", id);
                            Intent intent=new Intent(CommunistPartyActivity.this,ParkDetailsActivity.class);
                            //Bundle bundle=new Bundle();
                            //bundle.putString("id",id);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    });
                }else{
                    holder= (ViewHolder) ((Map) convertView.getTag()).get("holder");
                }
                Map<String,Object> map=data.get(position);
                holder.imageView.setImageBitmap((Bitmap)map.get("img"));
                holder.date.setText((String)map.get("date"));
                holder.title.setText((String)map.get("title"));
                return convertView;
            }
            class ViewHolder{
                public ImageView imageView;
                public TextView title;
                public TextView date;
            }
        };
        partListView.setAdapter(adapter);
        partListView.setOnItemClickListener(this);
    }

    @Override
    public void showAllPartyActivity(List<PartyActivityListBean.PartyActivitiesListBean> list, Bitmap[] bs) {
        for(int i=0;i<list.size();i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",bs[i]);
            map.put("title",list.get(i).getTitle());
            map.put("date",list.get(i).getCreateDate());
            map.put("id",list.get(i).getId());
            data.add(map);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                setListView();
            }
        });
    }

    @Override
    public void showPartyActivityInfo(PartyActivityBean bean,Bitmap bitmap) {

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

            }
        }
    };

    /**
     * ListViewItem 的监听事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s= (String) ((Map)view.getTag()).get("id");
        switch (s){
            case "1":
                Log.i("TestNum","1");
                break;
            case "":
                break;
        }
    }
}
