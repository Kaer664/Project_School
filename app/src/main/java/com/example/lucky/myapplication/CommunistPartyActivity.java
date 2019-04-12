package com.example.lucky.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.mo.bean.UserLoginBean;
import com.mo.presenter.PartyActivityPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IPartyActivityView;
import com.mo.view.IToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 党务活动
 */
public class CommunistPartyActivity extends AppCompatActivity implements IPartyActivityView, IToolsView {
    private Toolbar toolbar;
    private ListView partListView;
    private ToolsPresenter toolsPresenter=new ToolsPresenter(this, this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communist_party);
        toolBar();
        init();
        settoolbarName();
        PartyActivityPresenter partyActivityPresenter = new PartyActivityPresenter(this, this);
        partyActivityPresenter.getAllPartyActivity();
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbCommunistparty);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    @Override
    public void showRollingNotify(String content) {

    }

    @Override
    public void showLogin(UserLoginBean bean) {

    }

    @Override
    public void isReply(boolean b) {

    }

    @Override
    public void isFeedBack(boolean b) {

    }

    @Override
    public void isChangePass(boolean b) {

    }
    public void settoolbarName() {
        SharedPreferences sharedPreferences = toolsPresenter.readUserInfo();
        String userRealName = sharedPreferences.getString("userRealName", null);
        if (userRealName != null) {
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbCommunistpartyUserName);
            if(userRealName.length()<3){
                tvtbTempnewsUserName.setText(userRealName.toString());
            }else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }
}
