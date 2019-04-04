package com.example.lucky.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mo.bean.LearningGardenInfoBean;
import com.mo.bean.LearningGardenListBean;
import com.mo.presenter.LearningGardenPresenter;
import com.mo.view.ILearningGardenView;

import java.util.List;

public class StudyActivity extends AppCompatActivity implements ILearningGardenView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        LearningGardenPresenter lgp=new LearningGardenPresenter(this,this);
        lgp.getAllLearningGarden();
        lgp.getLearningGardenById("3");
    }

    @Override
    public void showLearningGardenList(List<LearningGardenListBean.LearningGardensListBean> list, Bitmap[] bitmaps) {
        for (int i=0;i<list.size();i++){
            Log.i("TestNum",list.get(i).getTitle());
        }
    }

    @Override
    public void showLearningGardenInfo(LearningGardenInfoBean bean, Bitmap bitmap) {
        Log.i("TestNum",bean.getReplyList().get(0).getId());
        Log.i("TestNum",bean.getReplyList().get(1).getUserName());
        Log.i("TestNum",bean.getReplyList().get(0).getUserName());
    }
}
