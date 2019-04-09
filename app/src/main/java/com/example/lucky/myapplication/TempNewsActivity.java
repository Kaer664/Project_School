package com.example.lucky.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mo.bean.PartyNewsBean;
import com.mo.bean.PartyNewsListBean;
import com.mo.presenter.PartyNewsPresenter;
import com.mo.view.IPartyNewsView;

import java.util.List;

public class TempNewsActivity extends AppCompatActivity implements IPartyNewsView {

    private TextView mTvNewsTitle;
    private TextView mTvNewsWriter;
    private ImageView mImgNewsPic;
    private TextView mTvNewContext;
    private PartyNewsPresenter partyNewsPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_news);
        init();
        int id = getIntent().getIntExtra("id", 1);
        partyNewsPresenter.getPartyNewsById(String.valueOf(id));
    }

    private void init() {
        mTvNewsTitle = (TextView) findViewById(R.id.tvNewsTitle);
        mTvNewsWriter = (TextView) findViewById(R.id.tvNewsWriter);
        mImgNewsPic = (ImageView) findViewById(R.id.imgNewsPic);
        mTvNewContext = (TextView) findViewById(R.id.tvNewContext);
        partyNewsPresenter=new PartyNewsPresenter(this,this);
    }

    @Override
    public void showAllPartyNews(List<PartyNewsListBean.PartyAffairsNewsListBean> list) {

    }

    @Override
    public void showPartyNewsInfo(final PartyNewsBean.PartyAffairsNewsBean bean, final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (bean!=null){
                    mTvNewsTitle.setText(bean.getTitle());
                    mTvNewsWriter.setText(bean.getWriterPersonName());
                    mTvNewContext.setText(bean.getWorkTask());
                    if (bitmap!=null){
                        mImgNewsPic.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }
}
