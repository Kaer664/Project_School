package com.example.lucky.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mo.bean.ScoreRankBean;
import com.mo.bean.UserScoreBean;
import com.mo.presenter.ScorePresenter;
import com.mo.view.IScoreView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingActivity extends AppCompatActivity implements IScoreView {

    private ListView listViewRank;
    private ScorePresenter scorePresenter;

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
}
