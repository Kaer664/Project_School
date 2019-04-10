package com.example.lucky.myapplication;

import android.graphics.Bitmap;
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

import com.mo.bean.BirthActivityBean;
import com.mo.bean.BirthdayMonthBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.BirthPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IBirthView;
import com.mo.view.IToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BirthdaydetailsActivity extends AppCompatActivity implements View.OnClickListener, IToolsView, IBirthView {
    private Toolbar toolbar;
    private ListView listViewBirth;
    private EditText etComment;
    private Button btnSendComment;
    private TextView textView, tvBirthdayTitle;
    private ImageView imgView;
    private ToolsPresenter toolsPresenter;
    private BirthPresenter birthPresenter;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdaydetails);
        toolBar();
        id = getIntent().getStringExtra("id");
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
        toolsPresenter = new ToolsPresenter(this, this);
        birthPresenter = new BirthPresenter(this, this);
        birthPresenter.getBirthActivityById(id);
//        initView();
        btnSendComment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String reply = etComment.getText().toString();
        if (reply != null || !reply.equals("")) {
            toolsPresenter.addReply(id, "党员生日活动", reply);
        } else {
            Toast.makeText(this, "请输入回复内容", Toast.LENGTH_SHORT).show();
        }
        etComment.setText("");
    }

    @Override
    public void showRollingNotify(String content) {

    }

    @Override
    public void showLogin(UserLoginBean bean) {

    }

    @Override
    public void isReply(final boolean b) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (b) {
                    Toast.makeText(BirthdaydetailsActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    birthPresenter.getBirthActivityById(id);
                } else {
                    Toast.makeText(BirthdaydetailsActivity.this, "上传失败，请稍后重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void isFeedBack(boolean b) {

    }

    @Override
    public void isChangePass(boolean b) {

    }

    @Override
    public void showBirthMonth(List<BirthdayMonthBean.UserListBean> list) {

    }

    @Override
    public void showBirthActivityList(List<BirthActivityBean.BirthActivitiesListBean> list, Bitmap[] bitmaps) {

    }

    @Override
    public void showBirthActivityInfo(final BirthActivityBean bean, final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (bean != null) {
                    BirthActivityBean.BirthActivitiesListBean bean1 = bean.getBirthActivitiesList().get(0);
                    tvBirthdayTitle.setText(bean1.getTitle());
                    textView.setText(bean1.getWorkTask());
                    if (bitmap != null) {
                        imgView.setImageBitmap(bitmap);
                    }
                    List<BirthActivityBean.ReplyListBean> replyList = bean.getReplyList();
                    List<Map<String, String>> replyData = new ArrayList<>();
                    for (BirthActivityBean.ReplyListBean replyListBean:replyList){
                        Map<String,String> map=new HashMap();
                        map.put("userName",replyListBean.getUserName());
                        map.put("content",replyListBean.getReplyContent());
                        replyData.add(map);
                    }
                    SimpleAdapter adapter = new SimpleAdapter(BirthdaydetailsActivity.this,
                            replyData,
                            R.layout.details_item
                            , new String[]{ "userName", "content"}
                            , new int[]{ R.id.itemName, R.id.itemMessage});
                    listViewBirth.setAdapter(adapter);
                }
            }
        });
    }
}
