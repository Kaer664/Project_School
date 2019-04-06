package com.example.lucky.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mo.bean.UserLoginBean;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IToolsView;

public class ChangePasswordActivity extends AppCompatActivity implements IToolsView {
    private EditText etNewPassword, etSureNewPassword;
    private Button btnChangePassword;
    private ToolsPresenter toolsPresenter;
    private EditText mEtOldPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initview();
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etNewPassword.getText().toString().equals("") && !etSureNewPassword.getText().toString().equals("")) {
                    if (etNewPassword.getText().toString().equals(etSureNewPassword.getText().toString())) {
                        String pwd = sharedPreferences.getString("pwd", "");
                        if (pwd!=""&&pwd.equals(mEtOldPassword.getText().toString())) {
                            toolsPresenter.changePass(mEtOldPassword.getText().toString(),etNewPassword.getText().toString());
                        } else {
                            Toast.makeText(ChangePasswordActivity.this, "本地密码错误！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "两次输入密码不同！", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(ChangePasswordActivity.this, "您还没有输入密码！", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initview() {
        mEtOldPassword = (EditText) findViewById(R.id.etOldPassword);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etSureNewPassword = (EditText) findViewById(R.id.etSurenewPassword);
        btnChangePassword = (Button) findViewById(R.id.btnChangePassword);

        toolsPresenter=new ToolsPresenter(this,this);
        sharedPreferences = toolsPresenter.readUserInfo();
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
    public void isFeedBack(boolean b) {

    }

    @Override
    public void isChangePass(final boolean b) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (b){
                    Toast.makeText(ChangePasswordActivity.this, "密码修改成功，请重新登录", Toast.LENGTH_SHORT).show();
                    //跳转之前把sharedPreferences里保存的密码删掉
                    toolsPresenter.saveUserInfo(null,null,null);
                    Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ChangePasswordActivity.this,"修改失败，请稍后重试",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
