package com.example.lucky.myapplication;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.mo.bean.UserLoginBean;
import com.mo.presenter.ToolsPresenter;
import com.mo.util.UpdateApp;
import com.mo.view.IToolsView;

public class SettingActivity extends AppCompatActivity implements IToolsView {
    private LinearLayout aboutUs, changePassword;
    private LinearLayout checkUpdate;

    private Button btnExitLogin;
    private ToolsPresenter toolsPresenter;
    private ProgressDialog progressDialog=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        toolBar();
        toolsPresenter = new ToolsPresenter(this, this);
        progressDialog=new ProgressDialog(this);
        settoolbarName();

        aboutUs = (LinearLayout) findViewById(R.id.aboutUs);
        checkUpdate = (LinearLayout) findViewById(R.id.checkUpdate);
        changePassword = (LinearLayout) findViewById(R.id.changePassword);
        btnExitLogin = (Button) findViewById(R.id.btnExitLogin);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog = new AlertDialog.Builder(SettingActivity.this)
                        .setTitle("关于我们")
                        .setMessage("By（软件创新工作室)\n蔡准、贾洪言、郭文彬、孙华林")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        checkUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(SettingActivity.this, new String[]{android
                        .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10001);
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
    public void settoolbarName() {
        SharedPreferences sharedPreferences = toolsPresenter.readUserInfo();
        String userRealName = sharedPreferences.getString("userRealName", null);
        if (userRealName != null) {
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbSettingName);
            if(userRealName.length()<3){
                tvtbTempnewsUserName.setText(userRealName.toString());
            }else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
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
    public void isChangePass(boolean b) {

    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 0 || PackageManager.PERMISSION_GRANTED != grantResults[0]) {
            Toast.makeText(this, "你拒绝了权限,无法检查更新!", Toast.LENGTH_LONG).show();
        } else {
            UpdateApp updateApp = new UpdateApp(this);
            updateApp.execute();
        }
    }
}
