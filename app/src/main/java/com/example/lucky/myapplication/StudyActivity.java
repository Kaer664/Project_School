package com.example.lucky.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        toolBar();
        init();
        settoolbarName();
        LearningGardenPresenter lgp = new LearningGardenPresenter(this, this);
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
        initView();
    }

    private void initView() {
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", R.drawable.study);
            map.put("title", "title" + i);
            map.put("date", "发布日期" + i);
            data.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.study_item
                , new String[]{"img", "title", "date"}
                , new int[]{R.id.imgStudyPic, R.id.tvStudyTitle, R.id.tvStudyinfoCreatetime});
        lvStudy.setAdapter(adapter);
    }

    @Override
    public void showLearningGardenList(List<LearningGardenListBean.LearningGardensListBean> list, Bitmap[] bitmaps) {
        for (int i = 0; i < list.size(); i++) {

        }
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
            if(userRealName.length()<3){
                tvtbTempnewsUserName.setText(userRealName.toString());
            }else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }

}
