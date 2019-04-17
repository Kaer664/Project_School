package com.example.lucky.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mo.bean.BirthActivityBean;
import com.mo.bean.BirthdayMonthBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.BirthPresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IBirthView;
import com.mo.view.IToolsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BirthdayActivity extends AppCompatActivity implements IBirthView, IToolsView {
    private Toolbar toolbar;
    private ListView lvBirthdayActivity;
    private TextView tvBirthdayName;
    private BirthPresenter bp;
    private ToolsPresenter toolsPresenter;
    private List<Map<String, Object>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        toolBar();
        init();
        bp.getAllBirthActivity();

    }

    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbbirthday);
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
        settoolbarName();
    }


    private void init(){
        lvBirthdayActivity = (ListView) findViewById(R.id.lvBirthdayActivity);
        tvBirthdayName= (TextView) findViewById(R.id.tvBirthdayName);
        bp=new BirthPresenter(this,this);
        bp.getBirthMonth();
        lvBirthdayActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id1 = (String) data.get(position).get("id");
                Intent intent = new Intent(BirthdayActivity.this, BirthdaydetailsActivity.class);
                intent.putExtra("id",id1);
                startActivity(intent);
            }
        });
    }
    private void initView() {
        BaseAdapter adapter1=new BaseAdapter() {
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
                ViewHolder holder=null;
                LayoutInflater inflater=LayoutInflater.from(BirthdayActivity.this);
                if(convertView==null){
                    holder=new ViewHolder();
                    convertView=inflater.inflate(R.layout.bitth_item,null);
                    holder.imgView= (ImageView) convertView.findViewById(R.id.item_img);
                    holder.birthTvName= (TextView) convertView.findViewById(R.id.birthTvName);
                    holder.birthTvMsg= (TextView) convertView.findViewById(R.id.birthTvMsg);
                    convertView.setTag(holder);
                }else{
                    holder= (ViewHolder) convertView.getTag();
                }
                Map<String,Object> map=data.get(position);
                holder.birthTvMsg.setText((String) map.get("date"));
                holder.birthTvName.setText((String) map.get("title"));
                holder.imgView.setImageBitmap((Bitmap) map.get("bitmap"));
                return convertView;
            }
            class ViewHolder {
                public ImageView imgView;
                public TextView birthTvName;
                public TextView birthTvMsg;
            }
        };

        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.bitth_item
                ,new String[]{"date","title","bitmap"}
                ,new int[]{R.id.birthTvMsg,R.id.birthTvName,R.id.item_img});
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if((view instanceof ImageView) & (data instanceof Bitmap)){
                    ImageView imgView = (ImageView)view;
                    imgView.setImageBitmap((Bitmap) data);
                }
                return false;
            }
        });
        lvBirthdayActivity.setAdapter(adapter1);
    }

    String name="";
    @Override
    public void showBirthMonth(List<BirthdayMonthBean.UserListBean> list) {
        StringBuffer sb=new StringBuffer();
        sb.append("本月过生日的党员：");
        for (int i=0;i<list.size();i++){
            BirthdayMonthBean.UserListBean userListBean = list.get(i);
            Log.i("TestNum","这是显示出生月份方法里面的数值："+userListBean.getName());
            sb.append(userListBean.getName()).append("，");
        }
        name=sb.substring(0,sb.length()-1).toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvBirthdayName.setText(name);
            }
        });
    }

    @Override
    public void showBirthActivityList(List<BirthActivityBean.BirthActivitiesListBean> list, Bitmap[] bitmaps) {
        data.clear();
        for (int i = 0; i < list.size(); i++) {
            BirthActivityBean.BirthActivitiesListBean bean=list.get(i);
            Map<String,Object> map=new HashMap<>();
            map.put("title",bean.getTitle());
            map.put("bitmap",bitmaps[i]);
            map.put("date",bean.getCreateDate());
            map.put("id",bean.getId());
            data.add(map);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initView();
            }
        });
    }
    public void settoolbarName() {
        toolsPresenter=new ToolsPresenter(this,this);
        SharedPreferences sharedPreferences = toolsPresenter.readUserInfo();
        String userRealName = sharedPreferences.getString("userRealName", null);
        if (userRealName != null) {
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbbirthdayUsername);
            if(userRealName.length()<3){
                tvtbTempnewsUserName.setText(userRealName.toString());
            }else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }

    @Override
    public void showBirthActivityInfo(BirthActivityBean bean, Bitmap bitmap) {
       int t= bean.getBirthActivitiesList().size();
        for(int i=0;i<t;i++){
            BirthActivityBean.BirthActivitiesListBean bean1 = bean.getBirthActivitiesList().get(i);
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
}
