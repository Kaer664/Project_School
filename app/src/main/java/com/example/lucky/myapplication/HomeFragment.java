package com.example.lucky.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 15632 on 2019/3/26.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{
    private TextView tvRollMessage,tvNews1,tvNews2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_home, null);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayout lineAnswer,linePart,lineAdvanced,lineVoice,lineStudy,lineBirthday;

        lineAnswer= (LinearLayout) getActivity().findViewById(R.id.lineAnswer);
        linePart= (LinearLayout) getActivity().findViewById(R.id.linePart);
        lineAdvanced= (LinearLayout) getActivity().findViewById(R.id.lineAdvanced);
        lineVoice= (LinearLayout) getActivity().findViewById(R.id.lineVoice);
        lineStudy= (LinearLayout) getActivity().findViewById(R.id.lineStudy);
        lineBirthday= (LinearLayout) getActivity().findViewById(R.id.lineBirthday);
        tvRollMessage= (TextView) getActivity().findViewById(R.id.tvRollMessage);
        tvRollMessage.setSelected(true);
        tvNews1= (TextView) getActivity().findViewById(R.id.tvNews1);
        tvNews2= (TextView) getActivity().findViewById(R.id.tvNews2);

        lineAnswer.setOnClickListener(this);
        linePart.setOnClickListener(this);
        lineAdvanced.setOnClickListener(this);
        lineVoice.setOnClickListener(this);
        lineStudy.setOnClickListener(this);
        lineBirthday.setOnClickListener(this);
        tvNews1.setOnClickListener(this);
        tvNews2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.lineAnswer:
                Log.i("TestNum","答题有奖");
                Toast.makeText(getActivity(),"答题有奖",Toast.LENGTH_LONG).show();
                intent=new Intent(getActivity(),AnswerActivity.class);
                startActivity(intent);
                break;
            case R.id.linePart:
                Toast.makeText(getActivity(),"党建活动",Toast.LENGTH_LONG).show();
                Log.i("TestNum","党建活动");
                intent=new Intent(getActivity(),CommunistPartyActivity.class);
                startActivity(intent);
                break;
            case R.id.lineAdvanced:
                Toast.makeText(getActivity(),"先进人物",Toast.LENGTH_LONG).show();
                Log.i("TestNum","先进人物");
                intent=new Intent(getActivity(),AdvancedfiguresActivity.class);
                startActivity(intent);
                break;
            case R.id.lineVoice:
                Toast.makeText(getActivity(),"党员心声",Toast.LENGTH_LONG).show();
                Log.i("TestNum","党员心声");
                intent=new Intent(getActivity(),ThinkingActivity.class);
                startActivity(intent);
                break;
            case R.id.lineStudy:
                Toast.makeText(getActivity(),"学习园地",Toast.LENGTH_LONG).show();
                Log.i("TestNum","学习园地");
                intent=new Intent(getActivity(),AdvancedfiguresActivity.class);
                startActivity(intent);
                break;
            case R.id.lineBirthday:
                Toast.makeText(getActivity(),"党员生日",Toast.LENGTH_LONG).show();
                Log.i("TestNum","党员生日");
                intent=new Intent(getActivity(),BirthdayActivity.class);
                startActivity(intent);
                break;
            case R.id.tvNews1:
                intentTempNews();
                break;
            case R.id.tvNews2:
                intentTempNews();
                break;
        }

    }
    public void intentTempNews(){
        Intent intent1=new Intent(getActivity(),TempNewsActivity.class);
        startActivity(intent1);
    }
}
