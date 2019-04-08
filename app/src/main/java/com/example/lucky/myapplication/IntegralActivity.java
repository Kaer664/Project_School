package com.example.lucky.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class IntegralActivity extends AppCompatActivity {

    private ExpandableListView exListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        exListView= (ExpandableListView) findViewById(R.id.exListView);
        BaseExpandableListAdapter adapter=new BaseExpandableListAdapter() {
            private int[] images = new int[]{
                    R.drawable.dati1,
                    R.drawable.dati1,
                    R.drawable.dati1
            };
            private String[] armTypes = new String[]{
                    "神族","虫族","人族"
            };
            private String[][] arms = new String[][]{
                    {"狂战士","龙骑士","黑暗圣堂"},
                    {"小狗","飞龙","自爆妃子"},
                    {"步兵","伞兵","护士mm"}
            };
            @Override
            public int getGroupCount() {
                return armTypes.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return arms[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return armTypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return arms[groupPosition][childPosition];
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
                TextView g1 = (TextView) v.findViewById(R.id.group1);
                TextView g2 = (TextView) v.findViewById(R.id.group1);
                g1.setText(armTypes[groupPosition]);
                g2.setText(armTypes[groupPosition]);
                return v;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                LayoutInflater inflater=LayoutInflater.from(IntegralActivity.this);
                View v=inflater.inflate(R.layout.child_item,null);
               TextView c= (TextView) v.findViewById(R.id.child1);
                c.setText(arms[groupPosition][childPosition]);
                return v;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }
        };
        exListView.setAdapter(adapter);
    }
}
