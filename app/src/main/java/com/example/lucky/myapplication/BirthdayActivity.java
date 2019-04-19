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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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


    private boolean mIsScroll;
    private void init(){
        lvBirthdayActivity = (ListView) findViewById(R.id.lvBirthdayActivity);
        tvBirthdayName= (TextView) findViewById(R.id.tvBirthdayName);
        bp=new BirthPresenter(this,this);
        bp.getBirthMonth();
//        lvBirthdayActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String id1 = (String) data.get(position).get("id");
//                Intent intent = new Intent(BirthdayActivity.this, BirthdaydetailsActivity.class);
//                intent.putExtra("id",id1);
//                startActivity(intent);
//            }
//        });
    }
    private int count=1;
    private void initView() {
        BaseAdapter adapter=new BaseAdapter() {
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
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder=null;
                LayoutInflater inflater=LayoutInflater.from(BirthdayActivity.this);
                if(convertView==null){
                    holder=new ViewHolder();
                    convertView=inflater.inflate(R.layout.bitth_item,null);
                    holder.title= (TextView) convertView.findViewById(R.id.birthTvName);
                    //holder.title.setMovementMethod(ScrollingMovementMethod.getInstance());  //设置textView可以滚动
                    holder.imageView = (ImageView) convertView.findViewById(R.id.item_img);
                    holder.date= (TextView) convertView.findViewById(R.id.birthTvMsg);
                    Map<String,Object> mapHolder=new HashMap<>();
                    mapHolder.put("holder",holder);
                    mapHolder.put("id",holder);
                    convertView.setTag(mapHolder);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String id=(String) data.get(position).get("id");
                            Log.i("TestNUm", id);
                            Intent intent=new Intent(BirthdayActivity.this,BirthdaydetailsActivity.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    });
                }else{
                    holder= (ViewHolder) ((Map) convertView.getTag()).get("holder");
                }
                Map<String,Object> map=data.get(position);
                        holder.imageView.setImageBitmap((Bitmap) map.get("bitmap"));
                holder.date.setText((String)map.get("date"));
                holder.title.setText((String)map.get("title"));
                return convertView;
            }
            class ViewHolder{
                public ImageView imageView;
                public TextView title;
                public TextView date;
            }
        };
        lvBirthdayActivity.setAdapter(adapter);
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
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                BirthActivityBean.BirthActivitiesListBean bean=list.get(i);
                Map<String,Object> map=new HashMap<>();
                map.put("title",bean.getTitle());
                map.put("bitmap",bitmaps[i]);
                map.put("date",bean.getCreateDate());
                map.put("id",bean.getId());
                data.add(map);
            }
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BirthdayActivity.this,"无法获取数据请稍后再试",Toast.LENGTH_LONG).show();
                }
            });
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(data.size()==0){
                    Toast.makeText(BirthdayActivity.this,"无法获取数据请稍后再试",Toast.LENGTH_LONG).show();
                }else {
                    initView();
                }
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
