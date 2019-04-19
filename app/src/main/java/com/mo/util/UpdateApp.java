package com.mo.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by 风雨诺 on 2019/4/15.
 */

public class UpdateApp extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private final String APP_NAME = "app-release1.apk";
    private String appFile = null;

    public UpdateApp(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        String json = HttpTools.postJson(context, Address.UPDATE_APP, null);

        try {
            JSONObject jsonObject = new JSONObject(json);
            appFile = jsonObject.getString("colorAppFile");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (APP_NAME.equals(appFile)) {
            return false;
        } else if (!(appFile.equals("") || appFile == null)) {
            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean b) {
        //判断需不需要更新
        if (b){
            //需要更新
            AlertDialog alertDialog=new AlertDialog.Builder(context)
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
        }else{
            //不需要更新
            Toast.makeText(context,"已安装最新版本",Toast.LENGTH_SHORT).show();
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
