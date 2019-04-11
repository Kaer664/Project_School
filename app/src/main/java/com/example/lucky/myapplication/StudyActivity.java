package com.example.lucky.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.lucky.myapplication.adapter.MyAdapter;
import com.mo.bean.LearningGardenInfoBean;
import com.mo.bean.LearningGardenListBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.LearningGardenPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.ILearningGardenView;
import com.mo.view.IToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyActivity extends AppCompatActivity implements ILearningGardenView, IToolsView {
    private Toolbar toolbar;
    private ToolsPresenter toolsPresenter = new ToolsPresenter(this, this);
    private LearningGardenPresenter lgp = new LearningGardenPresenter(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        toolBar();
        init();
        settoolbarName();
        lgp.getAllLearningGarden();
    }

    private void toolBar() {
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
    }

    private ListView lvStudy;
    private List<Map<String, Object>> data = new ArrayList<>();

    private void init() {
        lvStudy = (ListView) findViewById(R.id.lvStudy);
        lvStudy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id1= (String) mapList.get(position).get("id");
                Intent intent=new Intent(StudyActivity.this,StudydetailsActivity.class);
                intent.putExtra("id",id1);
                startActivity(intent);
            }
        });
    }

    private List<Map<String, Object>> mapList = new ArrayList<>();
    @Override
    public void showLearningGardenList(final List<LearningGardenListBean.LearningGardensListBean> list, Bitmap[] bitmaps) {

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            LearningGardenListBean.LearningGardensListBean bean = list.get(i);
            map.put("title",bean.getTitle());
            map.put("date",bean.getCreateDate());
            map.put("bitmap",bitmaps[i]);
            map.put("id",bean.getId());
            mapList.add(map);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyAdapter<Map<String, Object>> adapter=new MyAdapter<>(mapList,StudyActivity.this);
                lvStudy.setAdapter(adapter);
            }
        });

    }

    @Override
    public void showLearningGardenInfo(LearningGardenInfoBean bean, Bitmap bitmap) {
        Log.i("", "");
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
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbstudy);
            if (userRealName.length() < 3) {
                tvtbTempnewsUserName.setText(userRealName.toString());
            } else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }

}
