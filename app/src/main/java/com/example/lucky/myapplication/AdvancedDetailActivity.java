package com.example.lucky.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mo.bean.AdvancePersonBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.AdvancePersonPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IAdvancePersonView;
import com.mo.view.IToolsView;

import java.util.List;

public class AdvancedDetailActivity extends AppCompatActivity implements IToolsView, IAdvancePersonView {
    private Toolbar toolbar;
    private ToolsPresenter toolsPresenter = new ToolsPresenter(this, this);
    private AdvancePersonPresenter advancePersonPresenter;
    private ImageView imgPeople;
    private TextView tvName;
    private TextView tvMsg;
    private TextView tvDate;
    private TextView tvPeople;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_detail);
        advancePersonPresenter=new AdvancePersonPresenter(this,this);
        String id = getIntent().getStringExtra("id");
        if (id == null) {
            Toast.makeText(this, "数据可能有错，请稍后再试", Toast.LENGTH_LONG).show();
        } else {
            init();
            advancePersonPresenter.getAdvancePersonById(id);
        }

        toolBar();
        settoolbarName();
    }

    private void init() {
        imgPeople = (ImageView) findViewById(R.id.imgPeople);
        tvName = (TextView) findViewById(R.id.tvName);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvPeople = (TextView) findViewById(R.id.tvPeople);
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbPd);
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
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbParkdetailsUserName);
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

    @Override
    public void showAdvancePersonList(List<AdvancePersonBean.AdvancedPersonListBean> list, Bitmap[] bitmaps) {

    }

    @Override
    public void showAdvancePersonInfo(final AdvancePersonBean.AdvancedPersonListBean bean, final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (bitmap!=null) {
                    imgPeople.setImageBitmap(bitmap);
                }
                if (bean!=null){
                    tvName.setText(bean.getTitle());
                    tvMsg.setText(bean.getTypeName());
                    tvDate.setText(bean.getCreateDate());
                    tvPeople.setText(bean.getWorkTask());
                }
            }
        });
    }
}
