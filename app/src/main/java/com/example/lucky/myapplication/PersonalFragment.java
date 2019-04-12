package com.example.lucky.myapplication;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mo.bean.UserLoginBean;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IToolsView;

/**
 * Created by 15632 on 2019/3/26.
 */

public class PersonalFragment extends Fragment implements IToolsView {
    private TextView tvPersonalName;
    private ToolsPresenter toolsPresenter=new ToolsPresenter(getContext(), this);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_personal, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final LinearLayout settinglayout= (LinearLayout) getActivity().findViewById(R.id.settinglayout);
        settinglayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });
        final LinearLayout lineRaking= (LinearLayout) getActivity().findViewById(R.id.lineRaking);
        lineRaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),RankingActivity.class);
                startActivity(intent);
            }
        });
        final LinearLayout lineIntegral= (LinearLayout) getActivity().findViewById(R.id.lineIntegral);
        lineIntegral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),IntegralActivity.class);
                startActivity(intent);
            }
        });
        tvPersonalName= (TextView) getActivity().findViewById(R.id.tvPersonalName);
        settoolbarName();
    }
    public void settoolbarName() {
        SharedPreferences sharedPreferences = toolsPresenter.readUserInfo();
        String userRealName = sharedPreferences.getString("userRealName", null);
        tvPersonalName.setText(userRealName);
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
