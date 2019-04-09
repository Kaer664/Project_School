package com.example.lucky.myapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThinkingActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thinking);
        toolBar();
        init();
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbthinking);
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

    private EditText etMsg;
    private Button btnCom;

    /**
     * 用于初始化控件
     */
    private void init() {
        etMsg = (EditText) findViewById(R.id.etMsg);
        btnCom = (Button) findViewById(R.id.btnCom);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("留言成功");
        builder.setPositiveButton("确定",null);
        btnCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TestNum",etMsg.getText().toString());
                builder.create().show();
            }
        });
    }
}
