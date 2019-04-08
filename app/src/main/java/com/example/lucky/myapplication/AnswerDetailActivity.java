package com.example.lucky.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mo.bean.AnswerActivityListBean;
import com.mo.bean.QuestionInfoBean;
import com.mo.presenter.AnswerActivityPresenter;
import com.mo.view.IAnswerActivityView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerDetailActivity extends AppCompatActivity implements IAnswerActivityView {

    private TextView rbQuestionContext;
    private RadioButton rbAnswerA, rbAnswerB, rbAnswerC, rbAnswerD;
    private Button btnNext;
    private RadioGroup rgSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_detail);
        String id = getIntent().getStringExtra("id");
        AnswerActivityPresenter aap = new AnswerActivityPresenter(this, this);
        if(id==null){
            Toast.makeText(this,"数据可能有错，请稍后再试",Toast.LENGTH_LONG).show();
        }else {
            aap.getQuestionInfo(id);
            init();
        }
    }


    private void init() {
        rbAnswerA = (RadioButton) findViewById(R.id.rbAnswerA);
        rbAnswerB = (RadioButton) findViewById(R.id.rbAnswerB);
        rbAnswerC = (RadioButton) findViewById(R.id.rbAnswerC);
        rbAnswerD = (RadioButton) findViewById(R.id.rbAnswerD);
        rgSelect = (RadioGroup) findViewById(R.id.rgSelect);
        rbQuestionContext = (TextView) findViewById(R.id.rbQuestionContext);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size() == 0) {
                    Toast.makeText(AnswerDetailActivity.this, "请检查网络，或者题目无效，未请求到数据。", Toast.LENGTH_LONG).show();
                } else if (btnNext.getText().toString().equals("提交")) {
                    Log.i("TestNum", "这个时候变成提交了");
                } else if (count < sign) {
                    RadioButton rb = (RadioButton) findViewById(rgSelect.getCheckedRadioButtonId());
                    String selectString = rb.getText().toString();
                    String answerString = listAnswer.get(count);
                    if (selectString.equals(answerString)) {
                        score++;
                    }
                    if (count == sign - 1) {
                        btnNext.setText("提交");
                    } else {
                        setData();
                    }
                }
            }
        });
    }

    private void setData() {
        count = count + 1;
        String title = data.get(count).get("title");
        String a = data.get(count).get("a");
        String b = data.get(count).get("b");
        String c = data.get(count).get("c");
        String d = data.get(count).get("d");
        rbQuestionContext.setText(title);
        rbAnswerA.setText(a);
        rbAnswerB.setText(b);
        rbAnswerC.setText(c);
        rbAnswerD.setText(d);
    }


    @Override
    public void showAnswerActivityList(List<AnswerActivityListBean.UserAnswerActivityListBean> list) {

    }

    List<Map<String, String>> data = new ArrayList<>();
    private int count = -1;
    private int sign;
    private int score = 0;

    @Override
    public void showQuestionInfo(List<QuestionInfoBean.ProblemListBean> list) {
        sign = list.size();
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = new HashMap<>();
            QuestionInfoBean.ProblemListBean bean = list.get(i);
            map.put("title", bean.getTitle());
            map.put("a", bean.getSelectA());
            map.put("b", bean.getSelectB());
            map.put("c", bean.getSelectC());
            map.put("d", bean.getSelectD());
            Log.i("TestNum", bean.getAnswer());
            switch (bean.getAnswer()){
                case "A":
                    listAnswer.add(bean.getSelectA());
                    break;
                case "B":
                    listAnswer.add(bean.getSelectB());
                    break;
                case "C":
                    listAnswer.add(bean.getSelectC());
                    break;
                case "D":
                    listAnswer.add(bean.getSelectD());
                    break;
            }
            data.add(map);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                setData();
            }
        });
    }

    private List<String> listAnswer = new ArrayList<>();

    @Override
    public void isSave(boolean b) {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        }
    };
}
