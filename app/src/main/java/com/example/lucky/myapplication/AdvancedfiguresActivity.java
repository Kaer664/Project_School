package com.example.lucky.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mo.bean.AdvancePersonBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.AdvancePersonPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IAdvancePersonView;
import com.mo.view.IToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdvancedfiguresActivity extends AppCompatActivity implements IAdvancePersonView, IToolsView {
    private ToolsPresenter toolsPresenter;
    private GridView gridViewAdvanced;
    private Toolbar toolbar;
    //gridViewAdvanced
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advancedfigures);
        toolBar();
        init();
        toolsPresenter=new ToolsPresenter(this, this);
        settoolbarName();
        AdvancePersonPresenter ap=new AdvancePersonPresenter(this,this);
           ap.getAllAdvancePerson();
//        ap.getAdvancePersonById("5");
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
        gridViewAdvanced.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id1= (String) data.get(position).get("id");
                Intent intent=new Intent(AdvancedfiguresActivity.this,AdvancedDetailActivity.class);
                intent.putExtra("id",id1);
                startActivity(intent);
            }
        });
        //initView();
    }
    private List<Map<String,Object>> data=new ArrayList<>();

    private void initView() {
        BaseAdapter baseAdapter=new BaseAdapter() {
            @Override
            public int getCount() {
                Log.i("TestNum","data.size()+"+data.size());
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                Log.i("TestNum","data.size()+"+position);
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder=null;
                LayoutInflater inflater=LayoutInflater.from(AdvancedfiguresActivity.this);
                if(convertView==null){
                    holder=new ViewHolder();
                    convertView=inflater.inflate(R.layout.advanceditem,null);
                    holder.date= (TextView) convertView.findViewById(R.id.tvCreateDate);
                    holder.name= (TextView) convertView.findViewById(R.id.tvCharacterName);
                    holder.Message= (TextView) convertView.findViewById(R.id.tvCharacterAchievement);
                    holder.imgView= (ImageView) convertView.findViewById(R.id.imgCharacter);
                    String v=String.valueOf(convertView.findViewById(R.id.tvCreateDate));
                    convertView.setTag(holder);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(AdvancedfiguresActivity.this,AdvancedDetailActivity.class);
                            intent.putExtra("id",(String) data.get(position).get("id"));
                            startActivity(intent);
                        }
                    });
                }else{
                    holder= (ViewHolder) convertView.getTag();
                }
                holder.imgView.setImageBitmap((Bitmap) data.get(position).get("img"));
                holder.name.setText((String) data.get(position).get("name"));
                holder.date.setText((String) data.get(position).get("createDate"));
                holder.Message.setText((String) data.get(position).get("introduction"));
                return convertView;
            }
            class ViewHolder{
                public ImageView imgView;
                public TextView name;
                public TextView date;
                public TextView Message;
            }
        };
        gridViewAdvanced.setAdapter(baseAdapter);
    }

    /**
     * 获取先进人物列表
     * @param list
     * @param bitmaps
     */
    @Override
    public void showAdvancePersonList(List<AdvancePersonBean.AdvancedPersonListBean> list, Bitmap[] bitmaps) {
        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                AdvancePersonBean.AdvancedPersonListBean apb = list.get(i);
                Map<String, Object> map = new HashMap<>();
                map.put("img", bitmaps[i]);
                map.put("name", "-" + apb.getTitle() + "-");
                map.put("introduction", apb.getWorkTask());
                map.put("createDate", apb.getCreateDate());
                map.put("id", apb.getId());
                data.add(map);
            }
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(data.size()==0){
                    Toast.makeText(AdvancedfiguresActivity.this,"无法获取数据请稍后再试",Toast.LENGTH_LONG).show();
                }else{
                    initView();
                }
            }
        });

    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
        }
    };

    /**
     * 获取先进人物详情信息
     * @param bean
     * @param bitmap
     */
    @Override
    public void showAdvancePersonInfo(AdvancePersonBean.AdvancedPersonListBean bean, Bitmap bitmap) {
        Log.i("","");
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
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbAdvancedfigures);
            if(userRealName.length()<3){
                tvtbTempnewsUserName.setText(userRealName.toString());
            }else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }
}
