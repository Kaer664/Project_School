package com.example.lucky.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mo.bean.PartyNewsBean;
import com.mo.bean.PartyNewsListBean;
import com.mo.presenter.PartyNewsPresenter;
import com.mo.view.IPartyNewsView;

import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnClickListener,IPartyNewsView{
    private ImageView mImageView;
    private PartyNewsPresenter partyNewsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        partyNewsPresenter=new PartyNewsPresenter(this,this);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        partyNewsPresenter.getPartyNewsById("1");
    }

    @Override
    public void showAllPartyNews(List<PartyNewsListBean.PartyAffairsNewsListBean> list) {

    }

    @Override
    public void showPartyNewsInfo(PartyNewsBean.PartyAffairsNewsBean bean, final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(bitmap!=null){
                    mImageView.setImageBitmap(bitmap);
                    Toast.makeText(TestActivity.this,"图片加载完成",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(TestActivity.this,"没有图片",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
