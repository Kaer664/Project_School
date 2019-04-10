package com.example.lucky.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mo.bean.UserLoginBean;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IToolsView;

public class ThinkingActivity extends AppCompatActivity implements IToolsView {
    private Toolbar toolbar;
    private ToolsPresenter toolsPresenter;
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
    AlertDialog.Builder builder;
    private void init() {
        toolsPresenter=new ToolsPresenter(this,this);
        etMsg = (EditText) findViewById(R.id.etMsg);
        btnCom = (Button) findViewById(R.id.btnCom);
        builder= new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("留言成功,按确定退出界面");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("取消",null);
        btnCom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toolsPresenter.addFeedBack("党员心声",etMsg.getText().toString());
            }
        });
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
    public void isFeedBack(final boolean b) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (b){
                    etMsg.setText("");
                    builder.create().show();
                }else{
                    Toast.makeText(ThinkingActivity.this,"反馈失败，请检查网络稍后再试",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void isChangePass(boolean b) {

    }
}
