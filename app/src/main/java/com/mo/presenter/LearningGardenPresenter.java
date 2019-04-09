package com.mo.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.mo.bean.LearningGardenInfoBean;
import com.mo.model.LearningGardenDao;
import com.mo.model.impl.LearningGradenDaoImpl;
import com.mo.view.ILearningGardenView;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 * 学习园地控制层
 */

public class LearningGardenPresenter {
    private Context context;
    private LearningGardenDao dao= (LearningGardenDao) new LearningGradenDaoImpl();
    private ILearningGardenView view;

    public LearningGardenPresenter(Context context, ILearningGardenView view) {
        this.context = context;
        this.view = view;
    }
    public void getAllLearningGarden(){
        if(context!=null&&dao!=null){
            dao.getAllLearningGarden(context, new LearningGardenDao.LearningGardenListener() {
                @Override
                public void result(List list, Bitmap[] bitmaps) {
                    if (view != null) {
                        view.showLearningGardenList(list,bitmaps);
                    }
                }

                @Override
                public void result(LearningGardenInfoBean infoBean, Bitmap bitmap) {

                }
            });
        }
    }
    public void getLearningGardenById(String id){
        if(context!=null&&dao!=null){
            dao.getLearningGardenById(context, id, new LearningGardenDao.LearningGardenListener() {
                @Override
                public void result(List list, Bitmap[] bitmaps) {

                }

                @Override
                public void result(LearningGardenInfoBean infoBean, Bitmap bitmap) {
                    if (view!=null){
                        view.showLearningGardenInfo(infoBean,bitmap);
                    }
                }
            });
        }
    }
}
