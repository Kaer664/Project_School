package com.example.lucky.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mo.bean.LearningGardenInfoBean;
import com.mo.bean.LearningGardenListBean;
import com.mo.presenter.LearningGardenPresenter;
import com.mo.view.ILearningGardenView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyActivity extends AppCompatActivity implements ILearningGardenView,AdapterView.OnItemClickListener{
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        toolbar = (Toolbar) findViewById(R.id.tbstudy);
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
        init();
//        LearningGardenPresenter lgp=new LearningGardenPresenter(this,this);
//        lgp.getAllLearningGarden();
    }

    private ListView lvStudy;
    private List<Map<String,Object>> data=new ArrayList<>();
    private void init() {
        lvStudy= (ListView) findViewById(R.id.lvStudy);
        initView();
    }

    private void initView() {
        for(int i=0;i<3;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",R.drawable.study);
            map.put("title","title"+i);
            map.put("date","发布日期"+i);
            data.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.study_item
                ,new String[]{"img","title","date"}
                ,new int[]{R.id.imgStudyPic,R.id.tvStudyTitle,R.id.tvStudyinfoCreatetime});
        lvStudy.setAdapter(adapter);
        lvStudy.setOnItemClickListener(this);
    }

    @Override
    public void showLearningGardenList(List<LearningGardenListBean.LearningGardensListBean> list, Bitmap[] bitmaps) {
        for (int i=0;i<list.size();i++){
        }
    }

    @Override
    public void showLearningGardenInfo(LearningGardenInfoBean bean, Bitmap bitmap) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=null;
        switch (position){
            case 0:
                intent=new Intent(this,StudydetailsActivity.class);
                intent.putExtra("StudyDetailsId",position);
                startActivity(intent);
                break;
            case 1:
                intent=new Intent(this,StudydetailsActivity.class);
                intent.putExtra("StudyDetailsId",position);
                startActivity(intent);
                break;
            case 2:
                intent=new Intent(this,StudydetailsActivity.class);
                intent.putExtra("StudyDetailsId",position);
                startActivity(intent);
                break;
        }
    }
}
