package com.example.lucky.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.mo.bean.AdvancePersonBean;
import com.mo.presenter.AdvancePersonPresenter;
import com.mo.view.IAdvancePersonView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvancedfiguresActivity extends AppCompatActivity implements IAdvancePersonView {

    private GridView gridViewAdvanced;
    //gridViewAdvanced
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advancedfigures);
        init();
        AdvancePersonPresenter ap=new AdvancePersonPresenter(this,this);
        ap.getAllAdvancePerson();
    }


    private void init() {
        gridViewAdvanced= (GridView) findViewById(R.id.gridViewAdvanced);
        initView();
    }
    private List<Map<String,Object>> data=new ArrayList<>();

    private void initView() {
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.advanceditem
                ,new String[]{},new int[]{});
        gridViewAdvanced.setAdapter(adapter);
    }

    /**
     * 获取先进人物列表
     * @param list
     * @param bitmaps
     */
    @Override
    public void showAdvancePersonList(List<AdvancePersonBean.AdvancedPersonListBean> list, Bitmap[] bitmaps) {
        for(int i=0;i>list.size();i++){
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

    }
}
