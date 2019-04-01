package com.example.lucky.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mo.bean.PartyActivityBean;
import com.mo.bean.PartyActivityListBean;
import com.mo.presenter.PartyActivityPresenter;
import com.mo.view.IPartyActivityView;

import java.util.List;

public class ParkDetailsActivity extends AppCompatActivity implements IPartyActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String id=getIntent().getStringExtra("id");
        //Log.i("TestNum",id);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_details);
        PartyActivityPresenter partyActivityPresenter = new PartyActivityPresenter(this, this);
        partyActivityPresenter.getPartyActivityById(id);
    }

    @Override
    public void showAllPartyActivity(List<PartyActivityListBean.PartyActivitiesListBean> list) {

    }

    @Override
    public void showPartyActivityInfo(PartyActivityBean bean) {

    }
}
