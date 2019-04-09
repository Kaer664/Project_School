package com.example.lucky.myapplication;

import android.support.v7.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mo.presenter.ToolsPresenter;

public class SettingActivity extends AppCompatActivity {
    private LinearLayout aboutUs, changePassword;
    private Button btnExitLogin;
    private ToolsPresenter toolsPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        toolBar();
        toolsPresenter=new ToolsPresenter(this,null);
        aboutUs = (LinearLayout) findViewById(R.id.aboutUs);
        changePassword = (LinearLayout) findViewById(R.id.changePassword);
        btnExitLogin = (Button) findViewById(R.id.btnExitLogin);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog = new AlertDialog.Builder(SettingActivity.this)
                        .setTitle("关于我们")
                        .setMessage("Powered by Software Studio （软件工作室技术支持）" + "风雨喏、Lucky、把闪现给我交了")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        btnExitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转之前把sharedPreferences里保存的密码删掉
                toolsPresenter.saveUserInfo(null,null,null);
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(SettingActivity.this, "您已退出登录！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private Toolbar toolbar;
    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbSetting);
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
}
