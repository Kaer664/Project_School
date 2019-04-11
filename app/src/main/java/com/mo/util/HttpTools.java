package com.mo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.lucky.myapplication.util.PatchInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 风雨诺 on 2019/3/25.
 */

public class HttpTools {
    private static boolean checkNetWorkAction(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    public static String postJson(@NonNull Context context, @NonNull String url, @NonNull String key, @NonNull String value){
        if (!checkNetWorkAction(context)) {
            return null;
        }
        String s=null;
        OkHttpClient client=new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add(key,value);
        Request request=new Request.Builder()
                .url(url)
                .method("POST",builder.build())
                .build();
        try {
            Response response = client.newCall(request).execute();
            s = response.body().string();
            Log.i("test", "postJson: "+s);
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String postJson(@NonNull Context context, @NonNull String url, LinkedHashMap<String, String> map)  {
        if (!checkNetWorkAction(context)) {
            return null;
        }
        String s=null;
        OkHttpClient client=new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        if (map!=null){
            for (Map.Entry<String,String> entry:map.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        Request request=new Request.Builder()
                .url(url)
                .method("POST",builder.build())
                .build();
        try {
            Response response = client.newCall(request).execute();
            s = response.body().string();
            Log.i("test", "postJson: "+s);
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static Bitmap getBitmap(@NonNull Context context, @NonNull String url, String picName){
        if (!checkNetWorkAction(context)) {
            return null;
        }

        Bitmap s=null;
        OkHttpClient client=new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        Log.i("TestNum",url+picName);

        Request request=new Request.Builder()
                .url(url+picName)
                .method("POST",builder.build())
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
}
