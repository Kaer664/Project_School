package com.mo.presenter;

import android.content.Context;

import com.mo.model.AdvancePersonDao;
import com.mo.model.impl.AdvancePersonDaoImpl;
import com.mo.view.IAdvancePersonView;

import java.util.List;


/**
 * Created by 风雨诺 on 2019/3/27.
 */

public class AdvancePersonPresenter {
    private Context context;
    private AdvancePersonDao dao= (AdvancePersonDao) new AdvancePersonDaoImpl();
    private IAdvancePersonView view;

    public AdvancePersonPresenter(Context context, IAdvancePersonView view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 获取所有先进人物
     */
    public void getAllAdvancePerson(){
        if(context!=null&&dao!=null){
            dao.getAllAdvancePerson(context, new AdvancePersonDao.PersonListener() {
                @Override
                public void result(List list) {
                    if (view!=null){
                        view.showAdvancePersonList(list);
                    }
                }
            });
        }
    }

    /**
     * 通过id获取先进人物，获得的是列表
     * @param id
     */
    public void getAdvancePersonById(String id){
        if(context!=null&&dao!=null){
            dao.getAdvancePersonById(context,id, new AdvancePersonDao.PersonListener() {
                @Override
                public void result(List list) {
                    if (view!=null){
                        view.showAdvancePersonInfo(list);
                    }
                }
            });
        }
    }
}
