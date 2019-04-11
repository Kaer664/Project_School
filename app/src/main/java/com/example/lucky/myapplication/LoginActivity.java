package com.example.lucky.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mo.bean.UserLoginBean;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IToolsView;

public class LoginActivity extends AppCompatActivity implements IToolsView {
    private Toolbar tblogin;
    private Button btnLogin;
    private EditText etUsername, etPassword;
    private CheckBox cbautologin=null;
    private ToolsPresenter toolsPresenter=new ToolsPresenter(this,this);
    private String username="",pwd="";
    private int isRemember=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = toolsPresenter.readUserInfo();
        username = preferences.getString("username", "");
        pwd = preferences.getString("pwd","");
        if (username!=""&&pwd!=""){
            toolsPresenter.login(username,pwd);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setContentView(R.layout.activity_login);
        initview();
        /*测试数据*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cbautologin.isChecked()){
                    isRemember=0;
                }
                username=etUsername.getText().toString();
                pwd=etPassword.getText().toString();
                toolsPresenter.login(username,pwd);
            }
        });
    }


    private void initview() {
        tblogin = (Toolbar) findViewById(R.id.tblogin);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPwd);
        cbautologin = (CheckBox) findViewById(R.id.cbautologin);
    }

    @Override
    public void showRollingNotify(String content) {

    }

    @Override
    public void showLogin(final UserLoginBean bean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (bean != null) {
                    if(isRemember==1){
                        toolsPresenter.saveUserInfo(username,pwd,bean);
                    }else{
                        toolsPresenter.saveUserInfo(null,null,bean);
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "欢迎你" + bean.getUserRealName(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号密码不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
