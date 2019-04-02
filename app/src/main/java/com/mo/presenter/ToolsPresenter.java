package com.mo.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.mo.bean.UserLoginBean;
import com.mo.model.ToolsDao;
import com.mo.model.impl.ToolsDaoImpl;
import com.mo.view.IToolsView;

import java.util.LinkedHashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 风雨诺 on 2019/3/30.
 * 工具接口控制层
 */

public class ToolsPresenter {
    private Context context;
    private ToolsDao dao= (ToolsDao) new ToolsDaoImpl();
    private IToolsView view;

    public ToolsPresenter(Context context, IToolsView view) {
        this.context = context;
        this.view = view;
    }

    public void login(String username, String password){
        if(dao!=null&&context!=null&&view!=null){
            LinkedHashMap<String,String> map=new LinkedHashMap<>();
            map.put("username",username);
            map.put("password",password);
            dao.login(context, map, new ToolsDao.loginListener() {
                @Override
                public void result(UserLoginBean bean) {
                    if (view!=null){
                        view.showLogin(bean);
                    }
                }
            });
        }
    }

    /**
     * 获取滚动信息
     */
    public void getRollingNotify(){
        if(dao!=null&&context!=null&&view!=null){
            dao.getRollNotify(context,new ToolsDao.NotifyListener() {
                @Override
                public void result(String content) {
                    if (view!=null){
                        view.showRollingNotify(content);
                    }
                }
            });
        }
    }

    /**
     * 上传回复
     */
    public void addReply(LinkedHashMap<String,String> map){
        if(dao!=null&&context!=null&&view!=null){
            dao.addReply(context, map, new ToolsDao.AddReplyListener() {
                @Override
                public void result(boolean isReply) {
                    if (view!=null){
                        view.isReply(isReply);
                    }
                }
            });
        }
    }
    public void addFeedBack(LinkedHashMap<String, String> map){
        if(dao!=null&&context!=null&&view!=null){
            dao.addFeedBack(context, map, new ToolsDao.AddFeedListener() {
                @Override
                public void result(boolean isFeedBack) {
                    if (view!=null){
                        view.isFeedBack(isFeedBack);
                    }
                }
            });
        }
    }
    public void changePass(LinkedHashMap<String, String> map){
        if(dao!=null&&context!=null&&view!=null){
            dao.changePass(context, map, new ToolsDao.ChangePassListener() {
                @Override
                public void result(boolean isChange) {
                    view.isChangePass(isChange);
                }
            });
        }
    }
    private SharedPreferences preferences=null;
    public void saveUserInfo(String userName,String pwd,UserLoginBean bean){
        if (preferences==null){
            preferences= context.getSharedPreferences("userinfo", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",userName);
        editor.putString("pwd",pwd);
        if (bean!=null){
            editor.putString("userid",bean.getUserID());
            editor.putString("userrealname",bean.getUserRealName());
        }else{
            editor.putString("userid","");
            editor.putString("userrealname","");
        }
        editor.commit();
    }
    public SharedPreferences readUserInfo(){
        if (preferences==null){
            preferences= context.getSharedPreferences("userinfo", MODE_PRIVATE);
        }
        return preferences;
    }

}
