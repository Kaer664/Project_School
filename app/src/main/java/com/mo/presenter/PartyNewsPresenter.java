package com.mo.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.mo.bean.PartyNewsBean;
import com.mo.model.PartyNewsDao;
import com.mo.model.impl.PartyNewsDaoImpl;
import com.mo.view.IPartyNewsView;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/26.
 */

public class PartyNewsPresenter {
    private Context context;
    private IPartyNewsView view;
    private PartyNewsDao dao = (PartyNewsDao) new PartyNewsDaoImpl();

    public PartyNewsPresenter(Context context, IPartyNewsView view) {
        this.context = context;
        this.view = view;
    }

    public void getAllPartyNews() {
        if (dao != null && context != null) {
            dao.getAllPartyNews(context, new PartyNewsDao.LoginOkListener() {
                @Override
                public void result(List list) {
                    if (view != null) {
                        view.showAllPartyNews(list);
                    }
                }

                @Override
                public void result(PartyNewsBean.PartyAffairsNewsBean bean, Bitmap bitmap) {

                }
            });
        }
    }

    public void getPartyNewsById(String value) {
        if (dao != null && context != null) {
            dao.getPartyNewsById(context, value, new PartyNewsDao.LoginOkListener() {
                @Override
                public void result(List list){

                }

                @Override
                public void result(PartyNewsBean.PartyAffairsNewsBean bean, Bitmap bitmap) {
                    if (view != null) {
                        view.showPartyNewsInfo(bean,bitmap);
                    }
                }
            });
        }
    }
}
