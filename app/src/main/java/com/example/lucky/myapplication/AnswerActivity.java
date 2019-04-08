package com.example.lucky.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mo.bean.AnswerActivityListBean;
import com.mo.bean.QuestionInfoBean;
import com.mo.presenter.AnswerActivityPresenter;
import com.mo.view.IAnswerActivityView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerActivity extends AppCompatActivity implements IAnswerActivityView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        init();
        AnswerActivityPresenter ap=new AnswerActivityPresenter(this,this);
        ap.getActivityList();
    }

    private ListView listViewAnswer;
    private void init() {
        listViewAnswer= (ListView) findViewById(R.id.listViewAnswer);
    }

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
                LayoutInflater inflater=LayoutInflater.from(AnswerActivity.this);
                if(convertView==null){
                    holder=new ViewHolder();
                    convertView=inflater.inflate(R.layout.answer_item,null);
                    holder.date= (TextView) convertView.findViewById(R.id.tvDate);
                    holder.title= (TextView) convertView.findViewById(R.id.tvTitle);
                    holder.yesOrDo= (TextView) convertView.findViewById(R.id.tvYesOrNo);
                    holder.btnAnswer= (Button) convertView.findViewById(R.id.btnAnswer);
                    holder.btnAnswer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(AnswerActivity.this,AnswerDetailActivity.class);
                            intent.putExtra("id",data.get(position).get("id"));
                            startActivity(intent);
                        }
                    });
                    convertView.setTag(holder);
                }else{
                    holder= (ViewHolder) convertView.getTag();
                }
                Map<String,String> tMap=data.get(position);
                holder.title.setText(tMap.get("title"));
                holder.date.setText(tMap.get("start")+"--"+tMap.get("stop"));
                holder.yesOrDo.setText(tMap.get("do"));
                return convertView;
            }

            class ViewHolder{
                public TextView title;
                public TextView date;
                public TextView yesOrDo;
                public Button btnAnswer;
            }
        };
        listViewAnswer.setAdapter(adapter);
    }

    private List<Map<String,String>> data=new ArrayList<>();
    @Override
    public void showAnswerActivityList(List<AnswerActivityListBean.UserAnswerActivityListBean> list) {
        for(int i=0;i<list.size();i++){
            Map<String,String> map=new HashMap<>();
            AnswerActivityListBean.UserAnswerActivityListBean userBean = list.get(i);
            map.put("title",userBean.getTitle());
            map.put("start",userBean.getStartTime());
            map.put("stop",userBean.getEndTime());
            map.put("do",userBean.getYesOrNotDo());
            map.put("id",userBean.getId());
            data.add(map);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                initView();
            }
        });
    }

    @Override
    public void showQuestionInfo(List<QuestionInfoBean.ProblemListBean> list) {

    }

    @Override
    public void isSave(boolean b) {

    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
        }
    };
}
