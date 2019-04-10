package com.example.lucky.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.lucky.myapplication.bean.ChildBean;
import com.example.lucky.myapplication.bean.GroupBean;
import com.mo.bean.UserLoginBean;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IToolsView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class IntegralActivity extends AppCompatActivity implements IToolsView {

    private ExpandableListView exListView;
    private TextView tvSumScore;
    private List<GroupBean> groupList=new ArrayList<>();
    private List<List<ChildBean>> childBean=new ArrayList<>();
    private ToolsPresenter toolsPresenter=new ToolsPresenter(this, this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        toolBar();
        settoolbarName();
        for(int i=0;i<5;i++){
            GroupBean g=new GroupBean("测试内容","20");
            groupList.add(g);
        }
        for(int i=0;i<5;i++){
            List<ChildBean> list=new ArrayList<>();
            for(int j=0;j<3;j++){
                ChildBean c=new ChildBean("测试内容","5");
                list.add(c);
            }
            childBean.add(list);
        }
        exListView= (ExpandableListView) findViewById(R.id.exListView);
        tvSumScore= (TextView) findViewById(R.id.tvSumScore);

        BaseExpandableListAdapter adapter=new BaseExpandableListAdapter() {


            @Override
            public int getGroupCount() {
                return groupList.size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return childBean.get(groupPosition).size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return groupList.get(groupPosition);
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return childBean.get(groupPosition).get(childPosition);
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }


            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LayoutInflater inflater=LayoutInflater.from(IntegralActivity.this);
                View v=inflater.inflate(R.layout.group_item,null);
                TextView g1 = (TextView) v.findViewById(R.id.tvName);
                TextView g2 = (TextView) v.findViewById(R.id.tvScore);
                g1.setText(groupList.get(groupPosition).getTitle());
                g2.setText(groupList.get(groupPosition).getScore());
                return v;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                LayoutInflater inflater=LayoutInflater.from(IntegralActivity.this);
                View v=inflater.inflate(R.layout.child_item,null);
               TextView c= (TextView) v.findViewById(R.id.tvChildTitle);
               TextView cScore= (TextView) v.findViewById(R.id.tvChildScore);
                c.setText(childBean.get(groupPosition).get(childPosition).getTitle());
                cScore.setText(childBean.get(groupPosition).get(childPosition).getScore());
                return v;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }
        };
        exListView.setAdapter(adapter);
    }

    private Toolbar toolbar;
    private void toolBar() {
        toolbar = (Toolbar) findViewById(R.id.tbIntegral);
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
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbintegralname);
            tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
        }
    }
}
