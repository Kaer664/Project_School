package com.example.lucky.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText etNewPassword, etSureNewPassword;
    private Button btnChangePassword;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

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

                        //密码一样发送新密码到服务器

                        Toast.makeText(ChangePasswordActivity.this, "密码修改成功，请重新登录", Toast.LENGTH_SHORT).show();
                        //跳转之前把sharedPreferences里保存的密码删掉
                        editor.putString("password", "");
                        editor.commit();
                        Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
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
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etSureNewPassword = (EditText) findViewById(R.id.etSurenewPassword);
        btnChangePassword = (Button) findViewById(R.id.btnChangePassword);
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}
