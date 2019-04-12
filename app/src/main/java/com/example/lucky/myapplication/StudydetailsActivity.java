package com.example.lucky.myapplication;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lucky.myapplication.view.CommentView;
import com.mo.bean.LearningGardenInfoBean;
import com.mo.bean.LearningGardenListBean;
import com.mo.bean.PartyActivityBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.LearningGardenPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.util.Address;
import com.mo.view.ILearningGardenView;
import com.mo.view.IToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudydetailsActivity extends AppCompatActivity implements View.OnClickListener, IToolsView, ILearningGardenView {
    private VideoView vvStudyDetailsVideo;
    private TextView tvStudyDetailsTitle, tvStudyDetailsWriter, tvStudyDetailsContent, tvStudyReplyContent, tvStudyReplyName, tvStudyDetailsDownload;
    private EditText etStudyDetailsComment;
    private Button btnStudyDetailsSendComment;
    private LearningGardenPresenter lgp;
    private ImageView imgStudyDetails;
    private String id;
    private Toolbar toolbar;
    private LinearLayout lineStu;
    private int width;
    private int height;
    private ToolsPresenter toolsPresenter = new ToolsPresenter(this, this);
    boolean isReply=false;
    MediaController controller;
    RelativeLayout rlvvStudyDetailsVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studydetails);
        toolBar();
        settoolbarName();
        id = getIntent().getStringExtra("id");
        if (id == null) {
            Toast.makeText(this, "数据可能有错，请稍后再试", Toast.LENGTH_LONG).show();
        } else {
            init();
            lgp.getLearningGardenById(id); //通过ID获取详情

        }
    }


    private void init() {
        vvStudyDetailsVideo = (VideoView) findViewById(R.id.vvStudyDetailsVideo);
        tvStudyDetailsTitle = (TextView) findViewById(R.id.tvStudyDetailsTitle);
        tvStudyDetailsWriter = (TextView) findViewById(R.id.tvStudyDetailsWriter);
        lineStu= (LinearLayout) findViewById(R.id.lineStu);
        tvStudyDetailsContent = (TextView) findViewById(R.id.tvStudyDetailsContent);
        etStudyDetailsComment = (EditText) findViewById(R.id.etStudyDetailsComment);
        btnStudyDetailsSendComment = (Button) findViewById(R.id.btnStudyDetailsSendComment);
        btnStudyDetailsSendComment.setOnClickListener(this);
        tvStudyDetailsDownload = (TextView) findViewById(R.id.tvStudyDetailsDownload);
        tvStudyDetailsDownload.setOnClickListener(this);
        imgStudyDetails = (ImageView) findViewById(R.id.imgStudyDetails);
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        lgp = new LearningGardenPresenter(this, this);
        controller = new MediaController(this);//实例化控制器
         rlvvStudyDetailsVideo= (RelativeLayout) findViewById(R.id.rlvvStudyDetailsVideo);
    }

    public void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbStudyDetails);
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

    @Override
    public void onClick(View v) {   //点击发送按钮发送
        switch (v.getId()) {
            case R.id.btnStudyDetailsSendComment:
                String replyContent = etStudyDetailsComment.getText().toString().trim();
                if (isReply){
                    new AlertDialog.Builder(this).setMessage("您已发表观点").show();
                }else if (replyContent.length()<=10){
                    new AlertDialog.Builder(this).setMessage("每个观点至少10个字以上").show();
                } else{
                    toolsPresenter.addReply(id, "学习园地评论",replyContent);
                }

                break;
            case R.id.tvStudyDetailsDownload:
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Address.FILE_URL + tvStudyDetailsDownload.getText().toString()));
                request.setDestinationInExternalPublicDir("/download/", tvStudyDetailsDownload.getText().toString());
                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
                break;
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
                    Toast.makeText(StudydetailsActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    etStudyDetailsComment.setText("");
                    lgp.getLearningGardenById(id);
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

    public void settoolbarName() {
        SharedPreferences sharedPreferences = toolsPresenter.readUserInfo();
        String userRealName = sharedPreferences.getString("userRealName", null);
        if (userRealName != null) {
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbstudydetails);
            if (userRealName.length() < 3) {
                tvtbTempnewsUserName.setText(userRealName.toString());
            } else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }

    @Override
    public void showLearningGardenList(List<LearningGardenListBean.LearningGardensListBean> list, Bitmap[] bitmaps) {

    }

    private List<Map<String,Object>> data=new ArrayList<>();
    @Override
    public void showLearningGardenInfo(final LearningGardenInfoBean bean, final Bitmap bitmap) {

        List<LearningGardenInfoBean.ReplyListBean> listBeen = bean.getReplyList();
        for(int i=0;i<listBeen.size();i++){
            LearningGardenInfoBean.ReplyListBean replyListBean = listBeen.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("name", replyListBean.getUserName());
            map.put("date", "");
            map.put("headImg",R.drawable.img);
            map.put("content", replyListBean.getReplyContent());
            data.add(map);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //显示活动信息
                if (bean != null) {
                    LearningGardenInfoBean.LearningGardenListBean bean1 = bean.getLearningGardenList().get(0);
                    tvStudyDetailsTitle.setText(bean1.getTitle());//标题
                    tvStudyDetailsWriter.setText(bean1.getWriterPersonName());//创建者
                    tvStudyDetailsContent.setText(bean1.getWorkTask());//内容
                    tvStudyDetailsDownload.setText(bean1.getFileUrl());
                    if (bean1.getVideoUrl()!= null) {
                        rlvvStudyDetailsVideo.setVisibility(View.VISIBLE);
                        vvStudyDetailsVideo.setVideoURI(Uri.parse(Address.VIDAO_URL + bean1.getVideoUrl()));//视频
                        vvStudyDetailsVideo.setMediaController(controller);
                        controller.setMediaPlayer(vvStudyDetailsVideo);
                    }
                    if (bitmap!=null){
                        imgStudyDetails.setVisibility(View.VISIBLE);
                        imgStudyDetails.setImageBitmap(bitmap);
                    }
                }

                //显示用户评论
                String name = toolsPresenter.readUserInfo().getString("userRealName", "");
                if(data!=null){
                    for(int i=0;i<data.size();i++){
                        lineStu.addView(new CommentView(StudydetailsActivity.this,data.get(i)));
                        TextView t=new TextView(StudydetailsActivity.this);
                        t.setWidth(width);
                        t.setHeight(1);
                        t.setBackgroundColor(Color.BLACK);
                        lineStu .addView(t);
                        if (name.equals(data.get(i).get("name"))){
                            isReply=true;
                        }
                    }
                }
            }
        });
    }

}
