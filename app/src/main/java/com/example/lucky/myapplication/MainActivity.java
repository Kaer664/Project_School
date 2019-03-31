package com.example.lucky.myapplication;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity  {
    private Toolbar tbHomeActivity;
    private Button btnhome;
    private Button btnperson;
    private HomeFragment homeFragment;
    private PersonalFragment personalFragment;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbHomeActivity = (Toolbar) findViewById(R.id.tbHomeActivity);
        btnhome= (Button) findViewById(R.id.btnHomepage);
        btnperson= (Button) findViewById(R.id.btnPersonal);
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


    private void setTabSelection(int index){
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 0:
                if(homeFragment==null){
                    homeFragment = new HomeFragment();
                    ft.add(R.id.flmain, homeFragment);
                }else{
                    ft.show(homeFragment);
                }
                break;

            case 1:
                if(personalFragment==null){
                    personalFragment = new PersonalFragment();
                    ft.add(R.id.flmain, personalFragment);
                }
                ft.show(personalFragment);
                break;
        }
        ft.commit();
    }
    //用于隐藏fragment
    private void hideFragment(FragmentTransaction ft){
        if(homeFragment!=null){
            ft.hide(homeFragment);
        }if(personalFragment!=null){
            ft.hide(personalFragment);
        }
    }
    /*重写按钮返回事件，返回直接推出程序*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
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


}
