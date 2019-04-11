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

import com.mo.bean.ScoreRankBean;
import com.mo.bean.UserLoginBean;
import com.mo.bean.UserScoreBean;
import com.mo.presenter.ScorePresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IScoreView;
import com.mo.view.IToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingActivity extends AppCompatActivity implements IScoreView , IToolsView {

    private ListView listViewRank;
    private ScorePresenter scorePresenter;
    private ToolsPresenter toolsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        toolBar();
        listViewRank = (ListView) findViewById(R.id.listViewRank);
        scorePresenter = new ScorePresenter(this, this);
        scorePresenter.getScoreRank();
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
        settoolbarName();
    }

    public void settoolbarName() {
        toolsPresenter=new ToolsPresenter(this,this);
        SharedPreferences sharedPreferences = toolsPresenter.readUserInfo();
        String userRealName = sharedPreferences.getString("userRealName", null);
        if (userRealName != null) {
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbRankingName);
            if(userRealName.length()<3){
                tvtbTempnewsUserName.setText(userRealName.toString());
            }else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }

    @Override
    public void showUserScoreInfo(UserScoreBean bean) {

    }

    @Override
    public void showScoreRank(final List<ScoreRankBean.AllUserScoreListBean> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (list != null) {
                    List<Map<String, String>> data = new ArrayList<>();
                    for (int i=0;i<list.size();i++) {
                        ScoreRankBean.AllUserScoreListBean bean=list.get(i);
                        Map<String, String> map = new HashMap<>();
                        map.put("ranking", String.valueOf(i+1));
                        map.put("userName", bean.getUserName());
                        map.put("score", bean.getScore());
                        data.add(map);
                        SimpleAdapter adapter = new SimpleAdapter(RankingActivity.this, data, R.layout.ranking_item
                                , new String[]{"ranking","userName", "score"}
                                , new int[]{R.id.tvItemRanking, R.id.tvItemName, R.id.tvItemScore});
                        listViewRank.setAdapter(adapter);
                    }
                }
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
}
