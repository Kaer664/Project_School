package com.mo.presenter;

import android.content.Context;

import com.mo.model.BirthDao;
import com.mo.model.impl.BirthDaoImpl;
import com.mo.view.IBirthView;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 */

public class BirthPresenter {
    private Context context;
    private IBirthView view;
    private BirthDao dao = (BirthDao) new BirthDaoImpl();

    public BirthPresenter(Context context, IBirthView view) {
        this.context = context;
        this.view = view;
    }

    public void getBirthMonth() {
        if (context != null && dao != null) {
            dao.getBirthMonth(context, new BirthDao.BirthListener() {
                @Override
                public void result(List list) {
                    if (view != null) {
                        view.showBirthMonth(list);
                    }
                }
            });
        }
    }

    public void getAllBirthActivity() {
        if (context != null && dao != null) {
            dao.getAllBirthActivity(context, new BirthDao.BirthListener() {
                @Override
                public void result(List list) {
                    if (view != null) {
                        view.showBirthActivityList(list);
                    }
                }
            });
        }
    }

    public void getBirthActivityById(String id) {
        if (context != null && dao != null) {
            dao.getBirthActivityById(context, id, new BirthDao.BirthListener() {
                @Override
                public void result(List list) {
                    if (view != null) {
                        view.showBirthActivityList(list);
                    }
                }
            });
        }
    }
}
