package com.example.lucky.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingActivity extends AppCompatActivity {

    private ListView listViewRank;
    private List<Map<String,Object>> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        listViewRank= (ListView) findViewById(R.id.listViewRank);
        for(int i=0;i<3;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("ranking",i);
            map.put("img",R.drawable.qq_head2);
            map.put("name","name"+i);
            map.put("score","score"+i);
            data.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.ranking_item
                ,new String[]{"ranking","img","name","score"}
                ,new int[]{R.id.tvItemRanking,R.id.imgItem,R.id.tvItemName,R.id.tvItemScore});
        listViewRank.setAdapter(adapter);
    }
}
