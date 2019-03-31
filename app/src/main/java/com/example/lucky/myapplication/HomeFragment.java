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
import android.widget.Toast;

/**
 * Created by 15632 on 2019/3/26.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{
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

        lineAnswer.setOnClickListener(this);
        linePart.setOnClickListener(this);
        lineAdvanced.setOnClickListener(this);
        lineVoice.setOnClickListener(this);
        lineStudy.setOnClickListener(this);
        lineBirthday.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.lineAnswer:
                Log.i("TestNum","答题有奖");
                Toast.makeText(getActivity(),"答题有奖",Toast.LENGTH_LONG).show();
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
                break;
            case R.id.lineVoice:
                Toast.makeText(getActivity(),"党员心生",Toast.LENGTH_LONG).show();
                Log.i("TestNum","党员心生");
                break;
            case R.id.lineStudy:
                Toast.makeText(getActivity(),"学习园地",Toast.LENGTH_LONG).show();
                Log.i("TestNum","学习园地");
                break;
            case R.id.lineBirthday:
                Toast.makeText(getActivity(),"党员生日",Toast.LENGTH_LONG).show();
                Log.i("TestNum","党员生日");
                break;

        }

    }
}
