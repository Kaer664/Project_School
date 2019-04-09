package com.example.lucky.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucky.myapplication.view.CommentView;
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

public class ParkDetailsActivity extends AppCompatActivity implements IPartyActivityView, View.OnClickListener, IToolsView {
    private Toolbar toolbar;
    private EditText etBottom;
    private Button btnBottom;
    private TextView textView, tvWriterName, details_top_title;
    private ImageView imgView;
    private LinearLayout lineAdd;
    private ToolsPresenter toolsPresenter;
    private String activityId;
    private PartyActivityPresenter partyActivityPresenter;
    private String id;

    private int width;
    private int height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_details);
        toolBar();
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        partyActivityPresenter = new PartyActivityPresenter(this, this);
        id= getIntent().getStringExtra("id");
        if (id == null) {
            Toast.makeText(this, "数据可能有错，请稍后再试", Toast.LENGTH_LONG).show();
        } else {
            partyActivityPresenter.getPartyActivityById(id);
            init();
        }
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbParkdetails);
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

    /**
     * 方法用于绑定控件
     */
    private void init() {
        etBottom = (EditText) findViewById(R.id.etBottom);
        btnBottom = (Button) findViewById(R.id.btnBottom);
        textView = (TextView) findViewById(R.id.textView);
        tvWriterName = (TextView) findViewById(R.id.tvWriterName);
        details_top_title = (TextView) findViewById(R.id.details_top_title);
        imgView = (ImageView) findViewById(R.id.imgView);
        lineAdd= (LinearLayout) findViewById(R.id.lineAdd);
        btnBottom.setOnClickListener(this);
        toolsPresenter=new ToolsPresenter(this,this);
    }

    private void initView() {
       Log.i("TestNum",String.valueOf(data.size()));
        Map<String,Object> tMap=data.get(0);
        details_top_title.setText((String) tMap.get("title"));
        tvWriterName.setText((String) tMap.get("writer"));
        textView.setText((String) tMap.get("content"));
        imgView.setImageBitmap((Bitmap) tMap.get("img"));
        for(int i=0;i<reply_data.size();i++){
            lineAdd.addView(new CommentView(this,reply_data.get(i)));
            TextView t=new TextView(this);
            t.setWidth(width);
            t.setHeight(1);
            t.setBackgroundColor(Color.BLACK);
            lineAdd.addView(t);
        }
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
            activityId= javaBean.getId();
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
                etBottom.setText("");
                toolsPresenter.addReply(activityId,"党务活动评论",etBottom.getText().toString());
                break;
        }
    }

    @Override
    public void showRollingNotify(String content) {

    }

    @Override
    public void showLogin(UserLoginBean bean) {

    }

    @Override
    public void isReply(final boolean b) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (b){
                    Toast.makeText(ParkDetailsActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    partyActivityPresenter.getPartyActivityById(id);
                }
            }
        });
    }

    @Override
    public void isFeedBack(boolean b) {

    }

    @Override
    public void isChangePass(boolean b) {

    }
}
