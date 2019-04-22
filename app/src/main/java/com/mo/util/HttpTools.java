package com.mo.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.lucky.myapplication.util.PatchInputStream;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 风雨诺 on 2019/3/25.
 */

public class HttpTools {
    private static boolean checkNetWorkAction(Context context) {
        //判断是否有网络连接
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//        if (networkInfo == null) {
//            return false;
//        } else {
//            return true;
//        }
        //判断服务器ip地址是否可以ping通
        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 -w 1 " + Address.IP);
            int status = p.waitFor();
            if (status == 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String postJson(@NonNull Context context, @NonNull String url, @NonNull String key, @NonNull String value) {
        if (!checkNetWorkAction(context)) {
            return null;
        }
        String s = null;
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add(key, value);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", builder.build())
                .build();
        try {
            Response response = client.newCall(request).execute();
            s = response.body().string();
            Log.i("test", "postJson: " + s);
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String postJson(@NonNull Context context, @NonNull String url, LinkedHashMap<String, String> map) {
        if (!checkNetWorkAction(context)) {
            return null;
        }
        String s = null;
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .method("POST", builder.build())
                .build();
        try {
            Response response = client.newCall(request).execute();
            s = response.body().string();
            Log.i("test", "postJson: " + s);
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static Bitmap getBitmap(@NonNull Context context, @NonNull String url, String picName) {
        if (!checkNetWorkAction(context)) {
            return null;
        }

        Bitmap s = null;
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        Log.i("TestNum", url + picName);

        Request request = new Request.Builder()
                .url(url + picName)
                .method("POST", builder.build())
                .build();
        try {
            Response response = client.newCall(request).execute();
            InputStream is = response.body().byteStream();
            s = BitmapFactory.decodeStream(new PatchInputStream(is));
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void getFile(@NonNull Context context, @NonNull String url, String fileName) {
        if (!checkNetWorkAction(context)) {
            return;
        }

        Download download = new Download(fileName, context, url);
        download.execute();

    }

    static class Download extends AsyncTask<String, Integer, File> {

        private String fileName;
        private Context context;
        private String fileUrl;

        public Download(String fileName, Context context, String fileUrl) {
            this.fileName = fileName;
            this.context = context;
            this.fileUrl = fileUrl;
        }

        @Override
        protected File doInBackground(String... params) {
            OkHttpClient client = new OkHttpClient();
            File f = null;
            Request request = new Request.Builder()
                    .url(fileUrl + fileName)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                InputStream is = response.body().byteStream();
                f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
                    byte[] bytes = new byte[1024];
                    int led = bufferedInputStream.read(bytes);
                    while (led != -1) {
                        fos.write(bytes, 0, led);
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

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("正在下载文件,请稍后");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(File file) {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            Toast.makeText(context, "文件已下载到Download文件夹", Toast.LENGTH_SHORT).show();
            super.onPostExecute(file);
        }
    }
}
