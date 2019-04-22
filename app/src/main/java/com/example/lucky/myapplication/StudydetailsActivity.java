package com.example.lucky.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lucky.myapplication.ResizableImageView.CommentView;
import com.mo.bean.LearningGardenInfoBean;
import com.mo.bean.LearningGardenListBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.LearningGardenPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.util.Address;
import com.mo.util.HttpTools;
import com.mo.util.PermissionPageUtils;
import com.mo.util.UpdateApp;
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
    boolean isReply = false;
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
        lineStu = (LinearLayout) findViewById(R.id.lineStu);
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
        rlvvStudyDetailsVideo = (RelativeLayout) findViewById(R.id.rlvvStudyDetailsVideo);
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
                if (isReply) {
                    new AlertDialog.Builder(this).setMessage("此活动您已发表观点").setNegativeButton("确定", null).show();
                } else if (replyContent.length() <= 10) {
                    new AlertDialog.Builder(this).setMessage("每个观点至少10个字以上").setNegativeButton("确定", null).show();
                } else {
                    toolsPresenter.addReply(id, "学习园地评论", replyContent);
                }

                break;
            case R.id.tvStudyDetailsDownload:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setMessage("是否下载" + tvStudyDetailsDownload.getText().toString())
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                  检查权限是否开启，如果开启就进行下载
                                ActivityCompat.requestPermissions(StudydetailsActivity.this, new String[]{android
                                        .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10001);
                            }
                        })
                        .show();
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                intent.setData(Uri.parse(Address.FILE_URL + tvStudyDetailsDownload.getText().toString()));
//                startActivity(intent);
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
                    //lineStu.getChildCount();
                    lineStu.removeAllViews();
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

    private List<Map<String, Object>> data = new ArrayList<>();

    @Override
    public void showLearningGardenInfo(final LearningGardenInfoBean bean, final Bitmap bitmap) {
        data.clear();
        if (bean == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(StudydetailsActivity.this, "数据获取失败，请稍后重试", Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }
        List<LearningGardenInfoBean.ReplyListBean> listBeen = bean.getReplyList();
        for (int i = 0; i < listBeen.size(); i++) {
            LearningGardenInfoBean.ReplyListBean replyListBean = listBeen.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("name", replyListBean.getUserName());
            map.put("date", "");
            map.put("headImg", R.drawable.img);
            map.put("content", replyListBean.getReplyContent());
            data.add(map);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //显示活动信息
                if (bean != null) {
                    LearningGardenInfoBean.LearningGardenListBean bean1 = bean.getLearningGardenList().get(0);
                    tvStudyDetailsTitle.setText(bean1.getWriterPersonName());//标题
                    tvStudyDetailsWriter.setText(bean1.getTitle());//创建者
                    tvStudyDetailsContent.setText(bean1.getWorkTask());//内容
                    tvStudyDetailsDownload.setText(bean1.getFileUrl());
                    if (bean1.getVideoUrl() != null) {
                        rlvvStudyDetailsVideo.setVisibility(View.VISIBLE);
                        //new String(test.getBytes("UTF-8"))
                        vvStudyDetailsVideo.setVideoURI(Uri.parse(Address.VIDAO_URL + bean1.getVideoUrl()));//视频
                        vvStudyDetailsVideo.setMediaController(controller);
                        controller.setMediaPlayer(vvStudyDetailsVideo);
                    }
                    if (bitmap != null) {
                        imgStudyDetails.setVisibility(View.VISIBLE);
                        imgStudyDetails.setImageBitmap(bitmap);
                    }
                }

                //显示用户评论
                String name = toolsPresenter.readUserInfo().getString("userRealName", "");
                if (data.size() != 0) {
                    for (int i = 0; i < data.size(); i++) {
                        lineStu.addView(new CommentView(StudydetailsActivity.this, data.get(i)));
                        TextView t = new TextView(StudydetailsActivity.this);
                        t.setWidth(width);
                        t.setHeight(1);
                        t.setBackgroundColor(Color.BLACK);
                        lineStu.addView(t);
                        if (name.equals(data.get(i).get("name"))) {
                            isReply = true;
                        }
                    }
                }
            }
        });
    }

    /**
     * 检查权限的回调方法
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 0 || PackageManager.PERMISSION_GRANTED != grantResults[0]) {
            AlertDialog alertDialog=new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("使用软件需要相关权限，是否前往设置")
                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new PermissionPageUtils(StudydetailsActivity.this).jumpPermissionPage();
                        }
                    })
                    .show();
        }else {
            UpdateApp updateApp = new UpdateApp(this);
            updateApp.execute();
        }
    }

}
