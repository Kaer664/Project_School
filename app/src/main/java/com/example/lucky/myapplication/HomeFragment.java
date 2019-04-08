package com.example.lucky.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AndroidException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mo.bean.PartyNewsBean;
import com.mo.bean.PartyNewsListBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.PartyNewsPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IPartyNewsView;
import com.mo.view.IToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 15632 on 2019/3/26.
 */

public class HomeFragment extends Fragment implements View.OnClickListener, IToolsView, IPartyNewsView {
    private static final int SHOW_NITIFY = 0X0001;
    private static final int SHOW_PARTY_NEWS = 0X0002;
    private TextView tvRollMessage;
    private ToolsPresenter toolsPresenter;
    private PartyNewsPresenter partyNewsPresenter;
    private ListView mListView1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_NITIFY:
                    tvRollMessage.setText((String) msg.obj);
                    break;
                case SHOW_PARTY_NEWS:
//                    String[] ss= {"dsfsahdifhasdifa","sadfasdfasfdasf","dsfsahdifhasdifa","sadfasdfasfdasf"};
//                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,ss);
//                    mListView1.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_home, null);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayout lineAnswer, linePart, lineAdvanced, lineVoice, lineStudy, lineBirthday;

        lineAnswer = (LinearLayout) getActivity().findViewById(R.id.lineAnswer);
        linePart = (LinearLayout) getActivity().findViewById(R.id.linePart);
        lineAdvanced = (LinearLayout) getActivity().findViewById(R.id.lineAdvanced);
        lineVoice = (LinearLayout) getActivity().findViewById(R.id.lineVoice);
        lineStudy = (LinearLayout) getActivity().findViewById(R.id.lineStudy);
        lineBirthday = (LinearLayout) getActivity().findViewById(R.id.lineBirthday);


        tvRollMessage = (TextView) getActivity().findViewById(R.id.tvRollMessage);
        tvRollMessage.setSelected(true);

        toolsPresenter = new ToolsPresenter(getContext(), this);
        partyNewsPresenter = new PartyNewsPresenter(getContext(), this);
        toolsPresenter.getRollingNotify();
        partyNewsPresenter.getAllPartyNews();

        lineAnswer.setOnClickListener(this);
        linePart.setOnClickListener(this);
        lineAdvanced.setOnClickListener(this);
        lineVoice.setOnClickListener(this);
        lineStudy.setOnClickListener(this);
        lineBirthday.setOnClickListener(this);

        mListView1 = (ListView) getActivity().findViewById(R.id.listView1);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.lineAnswer:
                intent = new Intent(getActivity(), AnswerActivity.class);
                startActivity(intent);
                break;
            case R.id.linePart:
                intent = new Intent(getActivity(), CommunistPartyActivity.class);
                startActivity(intent);
                break;
            case R.id.lineAdvanced:
                intent = new Intent(getActivity(), AdvancedfiguresActivity.class);
                startActivity(intent);
                break;
            case R.id.lineVoice:
                intent = new Intent(getActivity(), ThinkingActivity.class);
                startActivity(intent);
                break;
            case R.id.lineStudy:
                intent = new Intent(getActivity(), StudyActivity.class);
                startActivity(intent);
                break;
            case R.id.lineBirthday:
                intent = new Intent(getActivity(), BirthdayActivity.class);
                startActivity(intent);
                break;
//            case R.id.tvNews1:
//                intentTempNews(1);
//                break;
//            case R.id.tvNews2:
//                intentTempNews(2);
//                break;
        }
    }

    public void intentTempNews(int i) {
        Intent intent1 = new Intent(getActivity(), TempNewsActivity.class);
        intent1.putExtra("id", i);
        startActivity(intent1);
    }

    @Override
    public void showRollingNotify(String content) {
        if (content != null) {
            Message msg = new Message();
            msg.obj = content;
            msg.what = SHOW_NITIFY;
            handler.sendMessage(msg);
        }
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
    public void showAllPartyNews(List<PartyNewsListBean.PartyAffairsNewsListBean> list) {
        if (list != null) {
            String[] ss = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ss[i] = list.get(i).getTitle();
            }
            Message msg = new Message();
            msg.obj = ss;
            msg.what = SHOW_PARTY_NEWS;
            handler.sendMessage(msg);
        }
    }

    @Override
    public void showPartyNewsInfo(PartyNewsBean.PartyAffairsNewsBean bean, Bitmap bitmap) {

    }
}
