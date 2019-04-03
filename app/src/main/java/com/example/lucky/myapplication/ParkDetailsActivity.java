package com.example.lucky.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mo.bean.PartyActivityBean;
import com.mo.bean.PartyActivityListBean;
import com.mo.presenter.PartyActivityPresenter;
import com.mo.view.IPartyActivityView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkDetailsActivity extends AppCompatActivity implements IPartyActivityView {

    private ListView details_list_view;
    private EditText etBottom;
    private Button btnBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String id=getIntent().getStringExtra("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_details);
        PartyActivityPresenter partyActivityPresenter = new PartyActivityPresenter(this, this);
        partyActivityPresenter.getPartyActivityById(id);
        init();
    }

    /**
     * 方法用于绑定控件
     */
    private void init() {
        details_list_view= (ListView) findViewById(R.id.details_list_view);
        etBottom= (EditText) findViewById(R.id.etBottom);
        btnBottom= (Button) findViewById(R.id.btnBottom);
       SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.details_item,new String[]{},new int[]{});
        details_list_view.setAdapter(adapter);
    }

    @Override
    public void showAllPartyActivity(List<PartyActivityListBean.PartyActivitiesListBean> list, Bitmap[] bs) {

    }



    List<Map<String,Object>> data=new ArrayList<>();
    List<Map<String,String>> reply_data=new ArrayList<>();
    @Override
    public void showPartyActivityInfo(PartyActivityBean bean,Bitmap bitmap) {
        //新闻消息内容,主要加载到本地中
        List<PartyActivityBean.PartyActivitiesListBean> list = bean.getPartyActivitiesList();
        for(int i=0;i<list.size();i++){
            PartyActivityBean.PartyActivitiesListBean javaBean=list.get(i);
            Map<String,Object> map=new HashMap<>();
            map.put("title",javaBean.getTitle());
            map.put("writer",javaBean.getWriterPersonName());
            map.put("content",javaBean.getWorkTask());
            data.add(map);
        }
        //用户评论内容,主要加载到本地中
        List<PartyActivityBean.ReplyListBean> userReply = bean.getReplyList();
        for(int i=0;i<userReply.size();i++){
            PartyActivityBean.ReplyListBean javaBean= userReply.get(i);
            Map<String,String> map=new HashMap<>();
            map.put("name",javaBean.getUserName());
            map.put("content",javaBean.getReplyContent());
            reply_data.add(map);
        }
    }
}
