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
import android.widget.Toast;

import com.example.lucky.myapplication.bean.ChildBean;
import com.example.lucky.myapplication.bean.GroupBean;
import com.mo.bean.ScoreRankBean;
import com.mo.bean.UserLoginBean;
import com.mo.bean.UserScoreBean;
import com.mo.presenter.ScorePresenter;
import com.mo.presenter.ToolsPresenter;
import com.mo.view.IScoreView;
import com.mo.view.IToolsView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegralActivity extends AppCompatActivity implements IScoreView , IToolsView {

    private ExpandableListView exListView;
    private TextView tvSumScore;
    private ScorePresenter scorePresenter;
    private List<GroupBean> groupList=new ArrayList<>();
    private List<List<ChildBean>> childBean=new ArrayList<>();
    private ToolsPresenter toolsPresenter;

    private List<GroupBean> groupList1=new ArrayList<>();
    private List<List<ChildBean>> childBean1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        toolBar();
        init();

        exListView= (ExpandableListView) findViewById(R.id.exListView);
        exListView.setGroupIndicator(null);
        tvSumScore= (TextView) findViewById(R.id.tvSumScore);
    }

    private void init() {
        scorePresenter=new ScorePresenter(this,this);
        scorePresenter.getUserScoreInfo();
    }

    private void initView(){
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
                TextView tvCount= (TextView) v.findViewById(R.id.tvCount);
                TextView cScore= (TextView) v.findViewById(R.id.tvChildScore);
                tvCount.setText(childBean.get(groupPosition).get(childPosition).getCount());
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
    };

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
    public void showUserScoreInfo(UserScoreBean bean) {
        if (bean == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(IntegralActivity.this, "无法获取数据请稍后再试", Toast.LENGTH_LONG).show();
                }
            });
        }else{
            final String scoreSum = bean.getScoreSum();
            String answerScore = bean.getAnswerActivityscoreSum();
            String replyScore = bean.getReplyscoreSum();
            GroupBean gb1=new GroupBean("答题活动得分",answerScore);
            GroupBean gb2=new GroupBean("回复得分",replyScore);
            groupList.add(gb1);
            groupList.add(gb2);
            List<UserScoreBean.UserAnswerActivityScorelistBean> activityScorelist = bean.getUserAnswerActivityScorelist();
            List<UserScoreBean.UserReplyScoreListBean> replyScoreList = bean.getUserReplyScoreList();

            //活动
            if (activityScorelist!=null||replyScoreList!=null){


                List<ChildBean> list=new ArrayList<>();
                int count1=0;
                for (UserScoreBean.UserAnswerActivityScorelistBean scoreBean:activityScorelist){
                    ChildBean cb=new ChildBean(scoreBean.getTitle(),scoreBean.getScore());
                    count1++;
                    cb.setCount(String.valueOf(count1));
                    list.add(cb);
                }


                final List<ChildBean> list1=new ArrayList<>();
                //回复
                int count=0;
                for (UserScoreBean.UserReplyScoreListBean scoreBean:replyScoreList){
                    ChildBean cb=new ChildBean(scoreBean.getTitle(),scoreBean.getScore());
                    count++;
                    cb.setCount(String.valueOf(count));
                    list1.add(cb);
                }
                childBean.add(list);
                childBean.add(list1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvSumScore.setText(scoreSum);
                        initView();
                    }
                });
            }
        }
    }

    public void settoolbarName() {
        toolsPresenter=new ToolsPresenter(this ,this);
        SharedPreferences sharedPreferences = toolsPresenter.readUserInfo();
        String userRealName = sharedPreferences.getString("userRealName", null);
        if (userRealName != null) {
            TextView tvtbTempnewsUserName = (TextView) findViewById(R.id.tvtbintegralname);
            if(userRealName.length()<3){
                tvtbTempnewsUserName.setText(userRealName.toString());
            }else {
                tvtbTempnewsUserName.setText(userRealName.substring(1).toString());
            }
        }
    }

    @Override
    public void showScoreRank(List<ScoreRankBean.AllUserScoreListBean> list) {

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
