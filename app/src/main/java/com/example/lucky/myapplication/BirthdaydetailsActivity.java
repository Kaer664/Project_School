package com.example.lucky.myapplication;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucky.myapplication.ResizableImageView.CommentView;
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
    private EditText etComment;
    private Button btnSendComment;
    private TextView textView, tvBirthdayTitle,tvWriterBName;
    private ImageView imgView;
    private ToolsPresenter toolsPresenter;
    private BirthPresenter birthPresenter;
    private String id;
    private LinearLayout line;
    private boolean isReply = false;
    List<Map<String, Object>> replyData;

    private int width;
    private int height;

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
        settoolbarName();
    }

    public void settoolbarName() {
        toolsPresenter = new ToolsPresenter(this, this);
        SharedPreferences sharedPreferences = toolsPresenter.readUserInfo();
        String userRealName = sharedPreferences.getString("userRealName", null);
        if (userRealName != null) {
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbbirthdayDetailsUsername);
            if (userRealName.length() < 3) {
                tvtbTempnewsUserName.setText(userRealName.toString());
            } else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    int f;

    private void init() {
        sp = this.getPreferences(MODE_PRIVATE);
        etComment = (EditText) findViewById(R.id.etComment);
        btnSendComment = (Button) findViewById(R.id.btnSendComment);
        textView = (TextView) findViewById(R.id.textView);
        tvWriterBName= (TextView) findViewById(R.id.tvWriterBName);
        tvBirthdayTitle = (TextView) findViewById(R.id.tvBirthdayTitle);
        imgView = (ImageView) findViewById(R.id.imgView);
        line = (LinearLayout) findViewById(R.id.line);
        toolsPresenter = new ToolsPresenter(this, this);
        birthPresenter = new BirthPresenter(this, this);
        birthPresenter.getBirthActivityById(id);
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        btnSendComment.setOnClickListener(this);
        f = sp.getInt("TestNumXXX" + id, 0);
         /* edittext 触摸获取焦点，软键盘弹起*/
        etComment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                etComment.setFocusable(true);
                etComment.setFocusableInTouchMode(true);
                return false;
            }

        });
    }

    @Override
    public void onClick(View v) {
        String reply = etComment.getText().toString();
        if (isReply) {
            new AlertDialog.Builder(this).setMessage("此活动您已发表观点").setNegativeButton("确定", null).show();
        } else if (reply.length() <= 10) {
            new AlertDialog.Builder(this).setMessage("每个观点至少10个字以上").setNegativeButton("确定", null).show();
        } else {
            toolsPresenter.addReply(id, "党员生日活动", reply);
        }
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
                    line.removeAllViews();
                    Toast.makeText(BirthdaydetailsActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    etComment.setText("");
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
                    tvWriterBName.setText(bean1.getWriterPersonName());
                    tvBirthdayTitle.setText(bean1.getTitle());
                    textView.setText(bean1.getWorkTask());
                    if (bitmap != null) {
                        imgView.setImageBitmap(bitmap);
                    }else{
                        imgView.setVisibility(View.GONE);
                    }

                    String name = toolsPresenter.readUserInfo().getString("userRealName", "");
                    List<BirthActivityBean.ReplyListBean> replyList = bean.getReplyList();
                    replyData = new ArrayList<>();
                    replyData.clear();
                    for (BirthActivityBean.ReplyListBean replyListBean : replyList) {
                        Map<String, Object> map = new HashMap();
                        map.put("name", replyListBean.getUserName());
                        if (name.equals(replyListBean.getUserName())){
                            isReply=true;
                        }
                        map.put("date", "");
                        map.put("headImg", R.drawable.img);
                        map.put("content", replyListBean.getReplyContent());
                        replyData.add(map);
                    }
                    for (int i = 0; i < replyData.size(); i++) {
                        line.addView(new CommentView(BirthdaydetailsActivity.this, replyData.get(i)));
                        TextView t = new TextView(BirthdaydetailsActivity.this);
                        t.setWidth(width);
                        t.setHeight(1);
                        t.setBackgroundColor(Color.BLACK);
                        line.addView(t);
                    }
                }
            }
        });
    }
}
