package com.example.lucky.myapplication;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mo.bean.UserLoginBean;
import com.mo.presenter.ToolsPresenter;
import com.mo.util.PermissionPageUtils;
import com.mo.util.UpdateApp;
import com.mo.view.IToolsView;

public class MainActivity extends AppCompatActivity implements IToolsView {
    private Toolbar tbHomeActivity;
    private LinearLayout btnhome;
    private LinearLayout btnperson;
    private HomeFragment homeFragment;
    private ImageView imgMainHome, imgMainPersonal;
    private PersonalFragment personalFragment;
    FragmentManager fm;
    private ToolsPresenter toolsPresenter = new ToolsPresenter(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //检查权限是否开启，如果开启就进行下载
        ActivityCompat.requestPermissions(this, new String[]{android
                .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10001);

        tbHomeActivity = (Toolbar) findViewById(R.id.tbHomeActivity);
        tbHomeActivity.setTitle("");
        setSupportActionBar(tbHomeActivity);
        settoolbarName();
        btnhome = (LinearLayout) findViewById(R.id.btnHomepage);
        btnperson = (LinearLayout) findViewById(R.id.btnPersonal);
        imgMainHome = (ImageView) findViewById(R.id.imgMainHome);
        imgMainPersonal = (ImageView) findViewById(R.id.imgMainPersonal);
        fm = getSupportFragmentManager();
        setTabSelection(0);
        btnhome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnHomepage:
                        setTabSelection(0);
                        break;
                    case R.id.btnPersonal:
                        setTabSelection(1);
                        break;
                }
            }
        });
        btnperson.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnHomepage:
                        setTabSelection(0);
                        break;
                    case R.id.btnPersonal:
                        setTabSelection(1);
                        break;
                }
            }
        });
    }


    private void setTabSelection(int index) {
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.flmain, homeFragment);
                } else {
                    ft.show(homeFragment);
                }
                imgMainHome.setImageDrawable(getResources().getDrawable(R.drawable.index1));
                imgMainPersonal.setImageDrawable(getResources().getDrawable(R.drawable.tech));
                break;

            case 1:
                if (personalFragment == null) {
                    personalFragment = new PersonalFragment();
                    ft.add(R.id.flmain, personalFragment);
                }
                ft.show(personalFragment);
                imgMainHome.setImageDrawable(getResources().getDrawable(R.drawable.index));
                imgMainPersonal.setImageDrawable(getResources().getDrawable(R.drawable.tech1));
                break;
        }
        ft.commit();
    }

    //用于隐藏fragment
    private void hideFragment(FragmentTransaction ft) {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (personalFragment != null) {
            ft.hide(personalFragment);
        }
    }

    /*重写按钮返回事件，返回直接推出程序*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            new AlertDialog.Builder(this)
                    .setMessage("确定退出系统吗？")
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    finish();
                                    System.exit(0);
                                }
                            }).show();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    public void showRollingNotify(String content) {

    }

    @Override
    public void showLogin(UserLoginBean bean) {

    }

    @Override
    public void isReply(boolean b) {

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
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbMainUserName);
            if (userRealName.length() < 3) {
                tvtbTempnewsUserName.setText(userRealName.toString());
            } else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }

    /**
     * 检查权限的回调方法
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
                            new PermissionPageUtils(MainActivity.this).jumpPermissionPage();
                            System.exit(0);
                        }
                    })
                    .show();
            Toast.makeText(this, "您拒绝了权限,无法检查更新!", Toast.LENGTH_LONG).show();
        }else {
            UpdateApp updateApp = new UpdateApp(this);
            updateApp.execute();
        }
    }
}
