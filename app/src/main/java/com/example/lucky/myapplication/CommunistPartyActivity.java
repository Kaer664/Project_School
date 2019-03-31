package com.example.lucky.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mo.bean.PartyActivityBean;
import com.mo.bean.PartyActivityListBean;
import com.mo.presenter.PartyActivityPresenter;
import com.mo.view.IPartyActivityView;

import java.util.List;


/**
 * 党务活动
 */
public class CommunistPartyActivity extends AppCompatActivity implements IPartyActivityView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communist_party);
        PartyActivityPresenter partyActivityPresenter = new PartyActivityPresenter(this, this);
        partyActivityPresenter.getAllPartyActivity();
    }

    @Override
    public void showAllPartyActivity(List<PartyActivityListBean.PartyActivitiesListBean> list) {
        for(int i=0;i<list.size();i++){
            Log.i("TestNum",list.get(i).getCreateDate());
        }
    }

    @Override
    public void showPartyActivityInfo(PartyActivityBean bean) {

    }
}
