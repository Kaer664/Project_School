package com.mo.presenter;

import android.content.Context;

import com.mo.bean.PartyActivityBean;
import com.mo.model.PartyActivityDao;
import com.mo.model.impl.PartyActivityDaoImpl;
import com.mo.view.IPartyActivityView;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 * 党务活动控制层
 */

public class PartyActivityPresenter {
    private Context context;
    private PartyActivityDao dao= (PartyActivityDao) new PartyActivityDaoImpl();
    private IPartyActivityView view;

    public PartyActivityPresenter(Context context, IPartyActivityView view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 获取所有党务活动列表
     */
    public void getAllPartyActivity(){
            if(context!=null&&dao!=null){
            dao.getAllPartyActivity(context, new PartyActivityDao.ActivityListListener() {
                @Override
                public void result(List list) {
                    if (view != null) {
                        view.showAllPartyActivity(list);
                    }
                }
            });
        }
    }

    /**
     * 通过id获得党务活动具体信息
     * @param id
     */
    public void getPartyActivityById(String id){
        if(context!=null&&dao!=null){
            dao.getPartyActivityById(context, id, new PartyActivityDao.ActivityListener() {
                @Override
                public void result(PartyActivityBean bean) {
                    if (view!=null){
                        view.showPartyActivityInfo(bean);
                    }
                }
            });
        }
    }
}
