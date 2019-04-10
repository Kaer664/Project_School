package com.example.lucky.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mo.bean.UserLoginBean;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingActivity extends AppCompatActivity implements IToolsView {

    private ListView listViewRank;
    private List<Map<String,Object>> data=new ArrayList<>();
    private ToolsPresenter toolsPresenter=new ToolsPresenter(this, this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        toolBar();
        settoolbarName();
        listViewRank= (ListView) findViewById(R.id.listViewRank);
        for(int i=0;i<3;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("ranking",i);
            map.put("img",R.drawable.img);
            map.put("name","name"+i);
            map.put("score","score"+i);
            data.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.ranking_item
                ,new String[]{"ranking","img","name","score"}
                ,new int[]{R.id.tvItemRanking,R.id.imgItem,R.id.tvItemName,R.id.tvItemScore});
        listViewRank.setAdapter(adapter);
    }

    private Toolbar toolbar;
    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbRanking);
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
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbRankingName);
            tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
        }
    }
}
