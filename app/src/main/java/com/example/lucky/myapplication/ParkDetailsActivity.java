package com.example.lucky.myapplication;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mo.bean.PartyActivityBean;
import com.mo.bean.PartyActivityListBean;
import com.mo.presenter.AnswerActivityPresenter;
import com.mo.presenter.PartyActivityPresenter;
import com.mo.view.IPartyActivityView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkDetailsActivity extends AppCompatActivity implements IPartyActivityView,View.OnClickListener {

    private ListView details_list_view;
    private EditText etBottom;
    private Button btnBottom;
    private TextView textView, tvWriterName, details_top_title;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_details);
        PartyActivityPresenter partyActivityPresenter = new PartyActivityPresenter(this, this);
        String id = getIntent().getStringExtra("id");
        if(id==null){
            Toast.makeText(this,"数据可能有错，请稍后再试",Toast.LENGTH_LONG).show();
        }else {
            partyActivityPresenter.getPartyActivityById(id);
            init();
        }
    }

    /**
     * 方法用于绑定控件
     */
    private void init() {
        details_list_view = (ListView) findViewById(R.id.details_list_view);
        etBottom = (EditText) findViewById(R.id.etBottom);
        btnBottom = (Button) findViewById(R.id.btnBottom);
        textView = (TextView) findViewById(R.id.textView);
        tvWriterName = (TextView) findViewById(R.id.tvWriterName);
        details_top_title = (TextView) findViewById(R.id.details_top_title);
        imgView = (ImageView) findViewById(R.id.imgView);
        btnBottom.setOnClickListener(this);
    }

    private void initView() {
       Log.i("TestNum",String.valueOf(data.size()));
        Map<String,Object> tMap=data.get(0);
        details_top_title.setText((String) tMap.get("title"));
        tvWriterName.setText((String) tMap.get("writer"));
        textView.setText((String) tMap.get("content"));
        imgView.setImageBitmap((Bitmap) tMap.get("img"));
        SimpleAdapter adapter=new SimpleAdapter(this,reply_data,R.layout.details_item
                ,new String[]{"headImg","name","content","date"}
                ,new int[]{R.id.itemHeadImg,R.id.itemName,R.id.itemMessage,R.id.itemDate});
        details_list_view.setAdapter(adapter);
    }

    @Override
    public void showAllPartyActivity(List<PartyActivityListBean.PartyActivitiesListBean> list, Bitmap[] bs) {

    }


    List<Map<String, Object>> data = new ArrayList<>();
    List<Map<String, Object>> reply_data = new ArrayList<>();

    @Override
    public void showPartyActivityInfo(PartyActivityBean bean, Bitmap bitmap) {
        //新闻消息内容,主要加载到本地中
        List<PartyActivityBean.PartyActivitiesListBean> list = bean.getPartyActivitiesList();
        for (int i = 0; i < list.size(); i++) {
            PartyActivityBean.PartyActivitiesListBean javaBean = list.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("title", javaBean.getTitle());
            map.put("writer", javaBean.getWriterPersonName());
            map.put("content", javaBean.getWorkTask());
            map.put("img",bitmap);
            data.add(map);
        }
        //用户评论内容,主要加载到本地中
        List<PartyActivityBean.ReplyListBean> userReply = bean.getReplyList();
        for (int i = 0; i < userReply.size(); i++) {
            PartyActivityBean.ReplyListBean javaBean = userReply.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("name", javaBean.getUserName());
            map.put("date", "date"+i);
            map.put("headImg",R.drawable.icon2);
            map.put("content", javaBean.getReplyContent());
            Log.i("TestNum",javaBean.getUserName());
            Log.i("TestNum",javaBean.getReplyContent());
            reply_data.add(map);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                initView();
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBottom:
                etBottom.getText().toString();
                break;
        }
    }
}
