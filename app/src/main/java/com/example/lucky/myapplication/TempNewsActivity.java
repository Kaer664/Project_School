package com.example.lucky.myapplication;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mo.bean.PartyNewsBean;
import com.mo.bean.PartyNewsListBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.PartyNewsPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IPartyNewsView;
import com.mo.view.IToolsView;

import java.util.List;

public class TempNewsActivity extends AppCompatActivity implements IPartyNewsView, IToolsView {

    private TextView mTvNewsTitle;
    private TextView mTvNewsWriter;
    private ImageView mImgNewsPic;
    private TextView mTvNewContext;
    private PartyNewsPresenter partyNewsPresenter;
    private ToolsPresenter toolsPresenter=new ToolsPresenter(this, this);
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_news);
        init();
        toolBar();
        settoolbarName();
        int id = getIntent().getIntExtra("id",-1);
        if (id==-1){
            Toast.makeText(this,"获取服务器数据失败，请尝试重新打开界面",Toast.LENGTH_SHORT).show();
        }else {
            partyNewsPresenter.getPartyNewsById(String.valueOf(id));
        }

    }

    private void init() {
        mTvNewsTitle = (TextView) findViewById(R.id.tvNewsTitle);
        mTvNewsWriter = (TextView) findViewById(R.id.tvNewsWriter);
        mImgNewsPic = (ImageView) findViewById(R.id.imgNewsPic);
        mTvNewContext = (TextView) findViewById(R.id.tvNewContext);
        partyNewsPresenter = new PartyNewsPresenter(this, this);

    }

    @Override
    public void showAllPartyNews(List<PartyNewsListBean.PartyAffairsNewsListBean> list) {

    }

    @Override
    public void showPartyNewsInfo(final PartyNewsBean.PartyAffairsNewsBean bean, final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (bean != null) {
                    mTvNewsTitle.setText(bean.getTitle());
                    mTvNewsWriter.setText(bean.getWriterPersonName());
                    mTvNewContext.setText(bean.getWorkTask());
                    if (bitmap != null) {
                        mImgNewsPic.setImageBitmap(bitmap);
                    }
               }
            }
        });
    }

    public void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbTempnews);
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

    public void settoolbarName() {
        SharedPreferences sharedPreferences = toolsPresenter.readUserInfo();
        String userRealName = sharedPreferences.getString("userRealName", null);
        if (userRealName != null) {
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbTempnewsUserName);
            if(userRealName.length()<3){
                tvtbTempnewsUserName.setText(userRealName.toString());
            }else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
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

}
