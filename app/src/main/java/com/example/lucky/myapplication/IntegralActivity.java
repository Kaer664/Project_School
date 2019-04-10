package com.example.lucky.myapplication;

import android.content.Context;
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
import com.mo.bean.ScoreRankBean;
import com.mo.bean.UserScoreBean;
import com.mo.presenter.ScorePresenter;
import com.mo.view.IScoreView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegralActivity extends AppCompatActivity implements IScoreView {

    private ExpandableListView exListView;
    private TextView tvSumScore;
    private ScorePresenter scorePresenter;
    private List<GroupBean> groupList=new ArrayList<>();
    private List<List<ChildBean>> childBean=new ArrayList<>();

    private List<GroupBean> groupList1=new ArrayList<>();
    private List<List<ChildBean>> childBean1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        toolBar();
        init();

        exListView= (ExpandableListView) findViewById(R.id.exListView);
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
        if (bean!=null){
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
                for (UserScoreBean.UserAnswerActivityScorelistBean scoreBean:activityScorelist){
                    ChildBean cb=new ChildBean(scoreBean.getTitle(),scoreBean.getScore());
                    list.add(cb);
                }


                List<ChildBean> list1=new ArrayList<>();
                //回复
                for (UserScoreBean.UserReplyScoreListBean scoreBean:replyScoreList){
                    ChildBean cb=new ChildBean(scoreBean.getTitle(),scoreBean.getScore());
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

    @Override
    public void showScoreRank(List<ScoreRankBean.AllUserScoreListBean> list) {

    }
}
