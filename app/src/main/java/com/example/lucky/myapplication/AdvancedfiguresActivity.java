package com.example.lucky.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.lucky.myapplication.util.PatchInputStream;
import com.mo.bean.AdvancePersonBean;
import com.mo.presenter.AdvancePersonPresenter;
import com.mo.view.IAdvancePersonView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvancedfiguresActivity extends AppCompatActivity implements IAdvancePersonView {

    private GridView gridViewAdvanced;
    private Toolbar toolbar;
    //gridViewAdvanced
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advancedfigures);
        toolBar();
        init();
        AdvancePersonPresenter ap=new AdvancePersonPresenter(this,this);
//        ap.getAllAdvancePerson();
//        ap.getAdvancePersonById("5");
        getConBitmap("http://172.18.1.168:8080/redplat//UpLoad/workPic/IMG_20160220_111800.jpg");
    }
    private void getConBitmap(final String path){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");  //设置请求的方式
                    con.setConnectTimeout(200);  //连接的超时时间   单位毫秒
                    con.setDoInput(true);       //设置希望读   还是写
                    con.connect();     //建立连接
                    //得到响应代码
                    int responseCode = con.getResponseCode();
                    if (responseCode == 200) {
                        //判断响应代码是不是200  200表示成功连接上了
                        Log.i("TEST", "OK");
                        InputStream is = con.getInputStream();    //可以连接了那么就开始读取数据
                        final Bitmap bitmap = BitmapFactory.decodeStream(new PatchInputStream(is));   //通过静态方法把数据转换为Bitmap类型数据
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //通过这样的机制来更改ui
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbAdvancedfigures);
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


    private void init() {
        gridViewAdvanced= (GridView) findViewById(R.id.gridViewAdvanced);
        initView();
    }
    private List<Map<String,Object>> data=new ArrayList<>();

    private void initView() {
        for(int i=0;i<3;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("img",R.drawable.zongshuji);
            map.put("name","-"+"name"+i+"-");
            map.put("introduction","introduction"+i);
            map.put("createDate","createDate"+i);
            data.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.advanceditem
                ,new String[]{"img","name","introduction","createDate"}
                ,new int[]{R.id.imgCharacter,R.id.tvCharacterName,R.id.tvCharacterAchievement,R.id.tvCreateDate});
        gridViewAdvanced.setAdapter(adapter);

        BaseAdapter baseAdapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
               View advancedItem= LayoutInflater.from(AdvancedfiguresActivity.this).inflate(R.layout.advanceditem,null);

                return null;
            }
        };

    }

    /**
     * 获取先进人物列表
     * @param list
     * @param bitmaps
     */
    @Override
    public void showAdvancePersonList(List<AdvancePersonBean.AdvancedPersonListBean> list, Bitmap[] bitmaps) {
        for(int i=0;i<list.size();i++){
            AdvancePersonBean.AdvancedPersonListBean apb = list.get(i);
            Map<String,Object> map=new HashMap<>();
            map.put("img",bitmaps[i]);
            map.put("name","-"+apb.getTitle()+"-");
            map.put("introduction",apb.getWorkTask());
            map.put("createDate",apb.getCreateDate());
            data.add(map);
        }
    }

    /**
     * 获取先进人物详情信息
     * @param bean
     * @param bitmap
     */
    @Override
    public void showAdvancePersonInfo(AdvancePersonBean.AdvancedPersonListBean bean, Bitmap bitmap) {
        Log.i("","");
    }
}
