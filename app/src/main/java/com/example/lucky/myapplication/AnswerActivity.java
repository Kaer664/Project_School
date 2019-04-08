package com.example.lucky.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mo.bean.AnswerActivityListBean;
import com.mo.bean.QuestionInfoBean;
import com.mo.presenter.AnswerActivityPresenter;
import com.mo.view.IAnswerActivityView;

import java.util.List;

public class AnswerActivity extends AppCompatActivity implements IAnswerActivityView{
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        toolbar = (Toolbar) findViewById(R.id.tbAnswer);
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

        AnswerActivityPresenter ap=new AnswerActivityPresenter(this,this);
        ap.getActivityList();
        Intent intent=new Intent(this,AnswerDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void showAnswerActivityList(List<AnswerActivityListBean.UserAnswerActivityListBean> list) {
        for(int i=0;i<list.size();i++){
            Log.i("TestNum",list.get(i).getStartTime());
        }
    }

    @Override
    public void showQuestionInfo(List<QuestionInfoBean.ProblemListBean> list) {

    }

    @Override
    public void isSave(boolean b) {

    }
}
