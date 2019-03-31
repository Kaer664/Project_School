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

public class LoginActivity extends AppCompatActivity {
  private   Toolbar tblogin;
  private Button btnLogin;
  private EditText etUsername,etPassword;
  private SharedPreferences sharedPreferences;
  private SharedPreferences.Editor editor;
  private CheckBox cbautologin;
  private String username="guo",password="111";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        /*测试数据*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().equals(username)&&etPassword.getText().toString().equals(password)){
                    editor.putString("username",etUsername.getText().toString());
                    if (cbautologin.isChecked())
                    editor.putString("password",etPassword.getText().toString());
                    editor.commit();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    if (password.toString().equals("111"))//初始密码，这里是写死的，不需要改成从服务器获取的
                    {
                        Toast.makeText(LoginActivity.this,"为了安全性，请您修改初始密码",Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"账户名或密码错误！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Initet();
        //setSupportActionBar(tblogin);
    }

    private void Initet() {
        etUsername.setText(sharedPreferences.getString("username",""));
        etPassword.setText(sharedPreferences.getString("password",""));
        if (etUsername.getText().toString().equals(username)&&etPassword.getText().toString().equals(password)){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            if (password.toString().equals("111"))//初始密码，这里是写死的，不需要改成从服务器获取的
            {
                Toast.makeText(LoginActivity.this,"为了安全性，请您修改初始密码",Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    private void initview() {
        tblogin= (Toolbar) findViewById(R.id.tblogin);
        btnLogin= (Button) findViewById(R.id.btnlogin);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPwd);
        sharedPreferences=getSharedPreferences("UserInfo",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        cbautologin= (CheckBox) findViewById(R.id.cbautologin);
    }
}
