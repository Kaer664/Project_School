package com.mo.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.mo.bean.BirthActivityBean;
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

                @Override
                public void result(List list, Bitmap[] bitmaps) {

                }

                @Override
                public void result(BirthActivityBean bean, Bitmap bitmap) {

                }
            });
        }
    }

    public void getAllBirthActivity() {
        if (context != null && dao != null) {
            dao.getAllBirthActivity(context, new BirthDao.BirthListener() {
                @Override
                public void result(List list) {

                }

                @Override
                public void result(List list, Bitmap[] bitmaps) {
                    if (view != null) {
                        view.showBirthActivityList(list,bitmaps);
                    }
                }

                @Override
                public void result(BirthActivityBean bean, Bitmap bitmap) {

                }
            });
        }
    }

    public void getBirthActivityById(String id) {
        if (context != null && dao != null) {
            dao.getBirthActivityById(context, id, new BirthDao.BirthListener() {
                @Override
                public void result(List list) {

                }

                @Override
                public void result(List list, Bitmap[] bitmaps) {

                }

                @Override
                public void result(BirthActivityBean bean, Bitmap bitmap) {
                    if (view != null) {
                        view.showBirthActivityInfo(bean,bitmap);
                    }
                }
            });
        }
    }
}
