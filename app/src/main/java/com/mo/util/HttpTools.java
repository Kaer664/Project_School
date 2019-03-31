package com.mo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

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
        StringBuffer sb = new StringBuffer();
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(3 * 1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setReadTimeout(5 * 1000);
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            String post = key + "=" + value;
            dos.write(post.getBytes());
            dos.flush();
            dos.close();
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                byte[] bytes = new byte[1024];
                int led = is.read(bytes, 0, 1024);
                while (led != -1) {
                    String s = new String(bytes, 0, led);
                    sb.append(s);
                    led = is.read(bytes, 0, 1024);
                }
            }
            Log.i("test", "postJson: "+sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String postJson(@NonNull Context context, @NonNull String url, Map<String,String> map)  {
        if (!checkNetWorkAction(context)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(3 * 1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setReadTimeout(5 * 1000);
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            if (map!=null){
                StringBuffer sb2=new StringBuffer();
                for (Map.Entry<String,String> entry:map.entrySet()){
                    sb2.append(entry.getKey()+"=");
                    sb2.append(entry.getValue()+"&");
                }
                sb2.deleteCharAt(sb2.length()-1);
                dos.write(sb2.toString().getBytes());
            }
            dos.flush();
            dos.close();
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                byte[] bytes = new byte[1024];
                int led = is.read(bytes, 0, 1024);
                while (led != -1) {
                    String s = new String(bytes, 0, led);
                    sb.append(s);
                    led = is.read(bytes, 0, 1024);
                }
            }
            Log.i("test", "postJson: "+sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static Bitmap getBitmap(@NonNull Context context, @NonNull String url, String picName){
        if (!checkNetWorkAction(context)) {
            return null;
        }
        Bitmap bitmap=null;
        try {
            URL u = new URL(url+picName);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
            }
            Log.i("Test", "getBitmap: 图片获取成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
