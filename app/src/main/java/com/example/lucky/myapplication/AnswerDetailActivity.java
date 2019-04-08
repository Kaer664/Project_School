package com.example.lucky.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class AnswerDetailActivity extends AppCompatActivity {
private Toolbar toolbar;
    private RadioButton rbAnswerA,rbAnswerB,rbAnswerC,rbAnswerD;
    private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_detail);
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
        init();

    }

    private void init() {
        rbAnswerA= (RadioButton) findViewById(R.id.rbAnswerA);
        rbAnswerB= (RadioButton) findViewById(R.id.rbAnswerB);
        rbAnswerC= (RadioButton) findViewById(R.id.rbAnswerC);
        rbAnswerD= (RadioButton) findViewById(R.id.rbAnswerD);
        btnNext= (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TestNum","A:"+String.valueOf(rbAnswerA.isChecked()));
                Log.i("TestNum","B:"+String.valueOf(rbAnswerB.isChecked()));
                Log.i("TestNum","C:"+String.valueOf(rbAnswerC.isChecked()));
                Log.i("TestNum","D:"+String.valueOf(rbAnswerD.isChecked()));
            }
        });
    }
}
