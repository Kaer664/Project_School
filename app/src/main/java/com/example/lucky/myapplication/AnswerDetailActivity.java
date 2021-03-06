package com.example.lucky.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mo.bean.AnswerActivityListBean;
import com.mo.bean.QuestionInfoBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.AnswerActivityPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IAnswerActivityView;
import com.mo.view.IToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerDetailActivity extends AppCompatActivity implements IAnswerActivityView, IToolsView, View.OnClickListener {
    private Toolbar toolbar;
    private TextView rbQuestionContext,now,sum;
    private RadioButton rbAnswerA, rbAnswerB, rbAnswerC, rbAnswerD;

    private Button btnNext;
    private RadioGroup rgSelect;
    private ToolsPresenter toolsPresenter = new ToolsPresenter(this, this);
    private AnswerActivityPresenter answerActivityPresenter = new AnswerActivityPresenter(this, this);
    private String id, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_detail);
        toolBar();
        settoolbarName();
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        // init2();
        AnswerActivityPresenter aap = new AnswerActivityPresenter(this, this);
        if (id == null) {
            Toast.makeText(this, "数据可能有错，请稍后再试", Toast.LENGTH_LONG).show();
        } else {
            aap.getQuestionInfo(id);
            init();
        }
    }


    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbAnswerdetail);
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

    private void init() {
        rbAnswerA = (RadioButton) findViewById(R.id.rbAnswerA);
        rbAnswerB = (RadioButton) findViewById(R.id.rbAnswerB);
        rbAnswerC = (RadioButton) findViewById(R.id.rbAnswerC);
        rbAnswerD = (RadioButton) findViewById(R.id.rbAnswerD);
        now= (TextView) findViewById(R.id.now);
        sum= (TextView) findViewById(R.id.sum);
        rbAnswerA.setOnClickListener(this);
        rbAnswerB.setOnClickListener(this);
        rbAnswerC.setOnClickListener(this);
        rbAnswerD.setOnClickListener(this);
        rgSelect = (RadioGroup) findViewById(R.id.rgSelect);
        rbQuestionContext = (TextView) findViewById(R.id.rbQuestionContext);

        btnNext = (Button) findViewById(R.id.btnNext);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size() == 0) {
                    Toast.makeText(AnswerDetailActivity.this, "请检查网络，或者题目无效，未请求到数据。", Toast.LENGTH_LONG).show();
                } else if (btnNext.getText().toString().equals("提交")) {
                    //提交数据
                    answerActivityPresenter.saveScore(id, title, String.valueOf(score));
                    btnNext.setEnabled(false);
                } else if (count < sign) {
                    RadioButton rb = (RadioButton) findViewById(rgSelect.getCheckedRadioButtonId());
                    String selectString = rb.getText().toString();
                    String answerString = listAnswer.get(count);
                    if (selectString.equals(answerString)) {//如果选择的和答案一样，就加一分
                        score++;
                    }
                    if (!(count == sign - 1)) {//如果没走到最后一项，就继续取数据
                        setData();
                    }
                }
            }
        });
    }

    private void setData() {
        count = count + 1;
        now.setText(String.valueOf(count+1));
        sum.setText(String.valueOf(sign));
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
        if (count == sign - 1) {//如果走到最后一项，将按钮变成提交
            btnNext.setText("提交");
        }
    }


    @Override
    public void showAnswerActivityList(List<AnswerActivityListBean.UserAnswerActivityListBean> list, String serverTime) {

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
            switch (bean.getAnswer()) {
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
    public void isSave(final boolean b) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (b) {
                    Toast.makeText(AnswerDetailActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AnswerDetailActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        }
    };

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

    public void settoolbarName() {
        SharedPreferences sharedPreferences = toolsPresenter.readUserInfo();
        String userRealName = sharedPreferences.getString("userRealName", null);
        if (userRealName != null) {
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbAnswerdetailUsername);
            if (userRealName.length() < 3) {
                tvtbTempnewsUserName.setText(userRealName.toString());
            } else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbAnswerA:
                rbAnswerA.setBackgroundColor(Color.parseColor("#57faff"));
                rbAnswerB.setBackgroundColor(Color.parseColor("#00000000"));
                rbAnswerC.setBackgroundColor(Color.parseColor("#00000000"));
                rbAnswerD.setBackgroundColor(Color.parseColor("#00000000"));
                break;
            case R.id.rbAnswerB:
                rbAnswerB.setBackgroundColor(Color.parseColor("#57faff"));
                rbAnswerA.setBackgroundColor(Color.parseColor("#00000000"));
                rbAnswerC.setBackgroundColor(Color.parseColor("#00000000"));
                rbAnswerD.setBackgroundColor(Color.parseColor("#00000000"));
                break;
            case R.id.rbAnswerC:
                rbAnswerC.setBackgroundColor(Color.parseColor("#57faff"));
                rbAnswerB.setBackgroundColor(Color.parseColor("#00000000"));
                rbAnswerA.setBackgroundColor(Color.parseColor("#00000000"));
                rbAnswerD.setBackgroundColor(Color.parseColor("#00000000"));
                break;
            case R.id.rbAnswerD:
                rbAnswerD.setBackgroundColor(Color.parseColor("#57faff"));
                rbAnswerB.setBackgroundColor(Color.parseColor("#00000000"));
                rbAnswerC.setBackgroundColor(Color.parseColor("#00000000"));
                rbAnswerA.setBackgroundColor(Color.parseColor("#00000000"));
                break;
        }
    }
}
