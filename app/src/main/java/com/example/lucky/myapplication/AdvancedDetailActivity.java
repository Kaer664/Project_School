package com.example.lucky.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdvancedDetailActivity extends AppCompatActivity {
    private TextView tvName;
    private TextView tvMsg;
    private TextView tvDate;
    private ImageView imgPeople;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_detail);
        String id = getIntent().getStringExtra("id");
        if (id == null) {
            Toast.makeText(this, "数据可能有错，请稍后再试", Toast.LENGTH_LONG).show();
        } else {
            init();
        }
    }


    private void init() {
        tvName= (TextView) findViewById(R.id.tvName);
        tvMsg= (TextView) findViewById(R.id.tvMsg);
        tvDate= (TextView) findViewById(R.id.tvDate);
        imgPeople= (ImageView) findViewById(R.id.imgPeople);
    }
}
