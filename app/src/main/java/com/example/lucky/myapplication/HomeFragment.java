package com.example.lucky.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.mo.bean.PartyNewsBean;
import com.mo.bean.PartyNewsListBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.PartyNewsPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IPartyNewsView;
import com.mo.view.IToolsView;

import java.util.List;

/**
 * Created by 15632 on 2019/3/26.
 */

public class HomeFragment extends Fragment implements View.OnClickListener, IToolsView, IPartyNewsView {
    private static final int SHOW_NITIFY = 0X0001;
    private static final int SHOW_PARTY_NEWS = 0X0002;
    private TextView tvRollMessage;
    private TextView tvNews1;
    private TextView tvNews2;
    private ToolsPresenter toolsPresenter;
    private PartyNewsPresenter partyNewsPresenter;
    int count = 0;
    ViewFlipper viewFlipper;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_NITIFY:
                    String s = "                                                                                                            ";
                    tvRollMessage.setText(msg.obj.toString() + s);
                    break;
                case SHOW_PARTY_NEWS:
                    String[] ss = (String[]) msg.obj;
                    count = 0;
                    int num = ss.length;
                    if (num % 2 != 0) {
                        num += 1;
                        for (int i = 0; i < num / 2; i++) {
                            LinearLayout linearLayout = new LinearLayout(getActivity());
                            linearLayout.setOrientation(LinearLayout.VERTICAL);
                            linearLayout.setGravity(Gravity.CENTER_VERTICAL);
                            tvNews1 = new TextView(getActivity());
                            tvNews2 = new TextView(getActivity());
                            tvNews1.setText(ss[count]);
                            count++;
                            tvNews1.setGravity(Gravity.CENTER_VERTICAL);
                            tvNews1.setTextColor(Color.BLACK);
                            tvNews1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    intentTempNews(count);
                                }
                            });
                            if (count == num - 1) {
                                linearLayout.addView(tvNews1);
                                viewFlipper.addView(linearLayout);
                            } else {

                                tvNews2.setText(ss[count]);
                                count++;
                                tvNews2.setGravity(Gravity.CENTER_VERTICAL);
                                tvNews2.setTextColor(Color.BLACK);
                                tvNews2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        intentTempNews(count);
                                    }
                                });
                                linearLayout.addView(tvNews1);
                                linearLayout.addView(tvNews2);
                                viewFlipper.addView(linearLayout);
                            }
                        }
                    } else {
                        for (int i = 0; i < num / 2; i++) {
                            LinearLayout linearLayout = new LinearLayout(getActivity());
                            linearLayout.setOrientation(LinearLayout.VERTICAL);
                            linearLayout.setGravity(Gravity.CENTER_VERTICAL);
                            tvNews1 = new TextView(getActivity());
                            tvNews2 = new TextView(getActivity());
                            tvNews1.setText(ss[count]);
                            count++;
                            tvNews1.setGravity(Gravity.CENTER_VERTICAL);
                            tvNews1.setTextColor(Color.BLACK);
                            tvNews1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    intentTempNews(count);
                                }
                            });
                            tvNews2.setText(ss[count]);
                            count++;
                            tvNews2.setGravity(Gravity.CENTER_VERTICAL);
                            tvNews2.setTextColor(Color.BLACK);
                            tvNews2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    intentTempNews(count);
                                }
                            });
                            linearLayout.addView(tvNews1);
                            linearLayout.addView(tvNews2);
                            viewFlipper.addView(linearLayout);
                        }
                    }
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
        viewFlipper = (ViewFlipper) getActivity().findViewById(R.id.view_flipper);

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
