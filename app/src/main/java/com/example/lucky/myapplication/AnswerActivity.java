package com.example.lucky.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mo.bean.AnswerActivityListBean;
import com.mo.bean.QuestionInfoBean;
import com.mo.presenter.AnswerActivityPresenter;
import com.mo.view.IAnswerActivityView;

import java.util.List;

public class AnswerActivity extends AppCompatActivity implements IAnswerActivityView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
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
