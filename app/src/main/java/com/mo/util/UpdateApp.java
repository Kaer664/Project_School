package com.mo.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by 风雨诺 on 2019/4/15.
 */

public class UpdateApp extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    //是否断网
    private boolean isDisconnect = false;
    private String appFile = null;

    private String getAPP_NAME() {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;

//        ---------------------
//                作者：南去良鱼
//        来源：CSDN
//        原文：https://blog.csdn.net/true_maitian/article/details/74963867
//        版权声明：本文为博主原创文章，转载请附上博文链接！

    }

    public UpdateApp(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        String json = HttpTools.postJson(context, Address.UPDATE_APP, null);
        if (json == null) {
            isDisconnect = true;
            return false;
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            appFile = jsonObject.getString("colorAppFile");
            if (appFile.equals(getAPP_NAME() + ".apk")) {
                return false;
            } else if (!(appFile.equals("") || appFile == null)) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean b) {
        //判断需不需要更新
        if (isDisconnect) {
            Toast.makeText(context, "连不上服务器，请检查网络并重试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (b) {
            //需要更新
            AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .setTitle("提示")
                    .setMessage("检测到新版本，是否更新")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
                            intent.setData(Uri.parse(Address.APP_FILE_URL + appFile));
                            context.startActivity(intent);
                        }
                    })
                    .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setCancelable(false)
                    .show();
        } else {
            //不需要更新
            Toast.makeText(context, "已安装最新版本", Toast.LENGTH_SHORT).show();
        }
    }

    class Download extends AsyncTask<String, Integer, File> {
        @Override
        protected File doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            File f=null;
            Request request = new Request.Builder()
                    .url(Address.APP_FILE_URL + appFile)
                    .method("POST", builder.build())
                    .build();

            try {
                Response response = client.newCall(request).execute();
                InputStream is = response.body().byteStream();
                f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), appFile);
                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
                    byte[] bytes = new byte[1024];
                    int led = bufferedInputStream.read(bytes);
                    int total = 0;
                    while (led != -1) {
                        fos.write(bytes, 0, led);
                        total += led;
                        publishProgress(total);
                        led = bufferedInputStream.read(bytes);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                response.body().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return f;
        }
    }
//    //开始下载
//    OkHttpClient client = new OkHttpClient();
//    FormBody.Builder builder = new FormBody.Builder();
//
//    Request request = new Request.Builder()
//            .url(Address.APP_FILE_URL + appFile)
//            .method("POST", builder.build())
//            .build();
//
//            try {
//        Response response = client.newCall(request).execute();
//        InputStream is = response.body().byteStream();
//        s = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), appFile);
//        try {
//            FileOutputStream fos = new FileOutputStream(s);
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
//            byte[] bytes=new byte[1024];
//            int led=bufferedInputStream.read(bytes);
//            int total=0;
//            while (led!=-1){
//                fos.write(bytes,0,led);
//                total+=led;
////                        publishProgress(total);
//                led=bufferedInputStream.read(bytes);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        response.body().close();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
}
