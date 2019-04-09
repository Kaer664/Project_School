package com.example.lucky.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BirthdaydetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listViewBirth;
    private EditText etComment;
    private Button btnSendComment;
    private TextView textView, tvBirthdayTitle;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdaydetails);
        toolBar();
        String id = getIntent().getStringExtra("id");
        if (id == null) {
            Toast.makeText(this, "数据可能有错，请稍后再试", Toast.LENGTH_LONG).show();
        } else {
            init();
        }
    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbbirthdayDetails);
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

    private void init() {
        listViewBirth = (ListView) findViewById(R.id.listViewBirth);
        etComment = (EditText) findViewById(R.id.etComment);
        btnSendComment = (Button) findViewById(R.id.btnSendComment);
        textView = (TextView) findViewById(R.id.textView);
        tvBirthdayTitle = (TextView) findViewById(R.id.tvBirthdayTitle);
        imgView = (ImageView) findViewById(R.id.imgView);
        initView();
    }

    private List<Map<String, Object>> replyData = new ArrayList<>();

    private void initView() {
        SimpleAdapter adapter = new SimpleAdapter(this, replyData, R.layout.details_item
                , new String[]{"headImg", "name", "content", "date"}
                , new int[]{R.id.itemHeadImg, R.id.itemName, R.id.itemMessage, R.id.itemDate});
        listViewBirth.setAdapter(adapter);
    }

}
