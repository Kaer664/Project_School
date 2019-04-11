package com.example.lucky.myapplication;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.mo.bean.LearningGardenInfoBean;
import com.mo.bean.LearningGardenListBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.LearningGardenPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.util.Address;
import com.mo.view.ILearningGardenView;
import com.mo.view.IToolsView;

import java.util.List;

public class StudydetailsActivity extends AppCompatActivity implements View.OnClickListener, IToolsView, ILearningGardenView {
    private VideoView vvStudyDetailsVideo;
    private TextView tvStudyDetailsTitle, tvStudyDetailsWriter, tvStudyDetailsContent, tvStudyReplyContent, tvStudyReplyName, tvStudyDetailsDownload;
    private EditText etStudyDetailsComment;
    private Button btnStudyDetailsSendComment;
    private LearningGardenPresenter lgp;
    private ImageView imgStudyDetails;
    private String id;
    private Toolbar toolbar;
    private ToolsPresenter toolsPresenter = new ToolsPresenter(this, this);

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
        tvStudyDetailsContent = (TextView) findViewById(R.id.tvStudyDetailsContent);
        tvStudyReplyContent = (TextView) findViewById(R.id.tvStudyReplyContent);
        tvStudyReplyName = (TextView) findViewById(R.id.tvStudyReplyName);
        etStudyDetailsComment = (EditText) findViewById(R.id.etStudyDetailsComment);
        btnStudyDetailsSendComment = (Button) findViewById(R.id.btnStudyDetailsSendComment);
        btnStudyDetailsSendComment.setOnClickListener(this);
        tvStudyDetailsDownload = (TextView) findViewById(R.id.tvStudyDetailsDownload);
        imgStudyDetails = (ImageView) findViewById(R.id.imgStudyDetails);
        lgp = new LearningGardenPresenter(this, this);

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
                toolsPresenter.addReply(id, "学习园地评论", etStudyDetailsComment.getText().toString());
                break;
            case R.id.tvStudyDetailsDownload:
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Address.GET_LEARNING_GARDEN_BY_ID + tvStudyDetailsDownload.getText().toString()));
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

    @Override
    public void showLearningGardenInfo(final LearningGardenInfoBean bean, final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //回复没有加载

                if (bean != null) {
                    LearningGardenInfoBean.LearningGardenListBean bean1 = bean.getLearningGardenList().get(0);
                    tvStudyDetailsTitle.setText(bean1.getTitle());//标题
                    tvStudyDetailsWriter.setText(bean1.getWriterPersonName());//创建者
                    tvStudyDetailsContent.setText(bean1.getWorkTask());//内容
                    vvStudyDetailsVideo.setVideoURI(Uri.parse(Address.VIDAO_URL + bean1.getVideoUrl()));//视频
                    vvStudyDetailsVideo.start();

                    //回复的列表，没到控件显示
                    List<LearningGardenInfoBean.ReplyListBean> listBeen = bean.getReplyList();

                    if (bitmap != null) {
                        imgStudyDetails.setImageBitmap(bitmap);//图片
                    }
                }
            }
        });
    }
}
