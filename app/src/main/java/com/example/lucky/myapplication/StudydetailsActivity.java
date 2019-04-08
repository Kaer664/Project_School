package com.example.lucky.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.mo.bean.LearningGardenInfoBean;
import com.mo.bean.LearningGardenListBean;
import com.mo.presenter.LearningGardenPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.util.Address;
import com.mo.view.ILearningGardenView;
import com.mo.view.IToolsView;

import java.util.List;

public class StudydetailsActivity extends AppCompatActivity implements ILearningGardenView, View.OnClickListener {
    private VideoView vvStudyDetailsVideo;
    private TextView tvStudyDetailsTitle, tvStudyDetailsWriter, tvStudyDetailsContent, tvStudyReplyContent, tvStudyReplyName;
    private EditText etStudyDetailsComment;
    private Button btnStudyDetailsSendComment;
    private Intent intent = getIntent();
    private int StudyDetailsId = -1;
    private LearningGardenPresenter lgp = null;
    private ImageView imgStudyDetails;
    private IToolsView view;
    private ToolsPresenter toolsPresenter=new ToolsPresenter(StudydetailsActivity.this,view);
    private String replyId;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studydetails);

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
        initview();
        if (StudyDetailsId == -1) {
            Log.e("本地异常", "学习园地ItemID异常");
        } else {
            lgp.getLearningGardenById(String.valueOf(StudyDetailsId));
        }

    }

    private void initview() {
        vvStudyDetailsVideo = (VideoView) findViewById(R.id.vvStudyDetailsVideo);
        tvStudyDetailsTitle = (TextView) findViewById(R.id.tvStudyDetailsTitle);
        tvStudyDetailsWriter = (TextView) findViewById(R.id.tvStudyDetailsWriter);
        tvStudyDetailsContent = (TextView) findViewById(R.id.tvStudyDetailsContent);
        tvStudyReplyContent = (TextView) findViewById(R.id.tvStudyReplyContent);
        tvStudyReplyName = (TextView) findViewById(R.id.tvStudyReplyName);
        etStudyDetailsComment = (EditText) findViewById(R.id.etStudyDetailsComment);
        btnStudyDetailsSendComment = (Button) findViewById(R.id.btnStudyDetailsSendComment);
        btnStudyDetailsSendComment.setOnClickListener(this);
        imgStudyDetails = (ImageView) findViewById(R.id.imgStudyDetails);
        StudyDetailsId = intent.getIntExtra("StudyDetailsId", -1);
        lgp = new LearningGardenPresenter(StudydetailsActivity.this, StudydetailsActivity.this);
    }

    @Override
    public void showLearningGardenList(List<LearningGardenListBean.LearningGardensListBean> list, Bitmap[] bitmaps) {

    }

    @Override
    public void showLearningGardenInfo(LearningGardenInfoBean bean, Bitmap bitmap) {

        List<LearningGardenInfoBean.LearningGardenListBean> list = bean.getLearningGardenList();
        List<LearningGardenInfoBean.ReplyListBean> replylist = bean.getReplyList();

        replyId=list.get(0).getId();

        if (list.get(0).getVideoUrl() != null) {
            vvStudyDetailsVideo.setVisibility(View.VISIBLE);
            vvStudyDetailsVideo.setVideoURI(Uri.parse(list.get(0).getVideoUrl()));
        }
        tvStudyDetailsTitle.setText(list.get(0).getTitle());
        tvStudyDetailsWriter.setText(list.get(0).getWriterPersonName());
        tvStudyDetailsContent.setText(list.get(0).getWorkTask());
        tvStudyDetailsWriter.setText(list.get(0).getWriterPersonName());

        if (bitmap != null) {
            imgStudyDetails.setVisibility(View.VISIBLE);
            imgStudyDetails.setImageBitmap(bitmap);
        }

        tvStudyReplyContent.setText(replylist.get(0).getReplyContent());
        tvStudyReplyName.setText(replylist.get(0).getUserName());
        //此处缺少加载word文档功能



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStudyDetailsSendComment:
                toolsPresenter.addReply(replyId,null,etStudyDetailsComment.getText().toString());
                break;
        }

    }
}
