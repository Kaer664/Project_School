package com.mo.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
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
    private String appFile = "error.apk";

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
        //判断有没有联网
        if (isDisconnect) {
            Toast.makeText(context, "连不上服务器，请检查网络并重试", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断需不需要更新
        if (b) {
            //需要更新
            AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .setTitle("提示")
                    .setMessage("检测到新版本，是否更新")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new Download().execute();
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
            File f=null;
            Request request = new Request.Builder()
                    .url(Address.APP_FILE_URL+appFile)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                InputStream is = response.body().byteStream();
                long l = response.body().contentLength();
                f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),appFile);
                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
                    byte[] bytes = new byte[1024];
                    int led = bufferedInputStream.read(bytes);
                    int total = 0;
                    while (led != -1) {
                        fos.write(bytes, 0, led);
                        total += led;
                        publishProgress((int)l,total);
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
        ProgressDialog downloadPd;
        @Override
        protected void onProgressUpdate(Integer... values) {
            downloadPd.setMax(values[0]/1024/1024);
            downloadPd.setProgress(values[1]/1024/1024);
        }

        @Override
        protected void onPreExecute() {
            if (downloadPd==null){
                downloadPd=new ProgressDialog(context);
                downloadPd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                downloadPd.setProgressNumberFormat("%1d Mb /%2d Mb");
                downloadPd.setMessage("正在下载文件");
                downloadPd.setCancelable(false);
                downloadPd.show();
            }
        }

        @Override
        protected void onPostExecute(File file) {
            Toast.makeText(context,"文件下载成功",Toast.LENGTH_SHORT).show();
            if (downloadPd!=null){
                downloadPd.cancel();
            }
            //调用系统安装程序
            Intent intent =new Intent(Intent.ACTION_VIEW);
            //判断是否是AndroidN以及更高的版本
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N) {
                //添加这句，安装软件时会多一个相同的apk文件
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri contentUri = FileProvider.getUriForFile(context,"com.example.lucky.myapplication.provider",file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(contentUri,"application/vnd.android.package-archive");
            }else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            context.startActivity(intent);
        }
    }
}
