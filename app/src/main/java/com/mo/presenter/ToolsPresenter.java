package com.mo.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.mo.bean.UserLoginBean;
import com.mo.model.ToolsDao;
import com.mo.model.impl.ToolsDaoImpl;
import com.mo.view.IToolsView;

import java.util.LinkedHashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 风雨诺 on 2019/3/30.
 * 工具接口控制层
 *
 */

public class ToolsPresenter {
    private static Context context;
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
    synchronized public void getRollingNotify(){
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
    public void addReply(String subId,String subTitle,String replyContent){
        if(dao!=null&&context!=null&&view!=null){
            SharedPreferences preferences = readUserInfo();
            String userId = preferences.getString("userId", "");
            String userName = preferences.getString("userRealName", "");
            if (userId==""||userName==""){
                Log.i("test", "文件中没有用户id或用户名称");
                return;
            }

            LinkedHashMap<String,String> map=new LinkedHashMap<>();
            map.put("userID",userId);
            map.put("userName",userName);
            map.put("subID",subId);
            map.put("subTitle",subTitle);
            map.put("replyContent",replyContent);

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

    /**
     * 用户反馈
     * 用户id和用户姓名直接从文件获取
     * @param title 反馈标题
     * @param content 反馈内容
     */
    public void addFeedBack(String title,String content){
        if(dao!=null&&context!=null&&view!=null){
            SharedPreferences preferences = dao.readUserInfo(context);
            String userId = preferences.getString("userId", "");
            String userName = preferences.getString("userRealName", "");
            if (userId==""||userName==""){
                Log.i("test", "文件中没有用户id或用户名称");
                return;
            }

            LinkedHashMap<String,String> map=new LinkedHashMap<>();
            map.put("title",title);
            map.put("content",content);
            map.put("userID",userId);
            map.put("userName",userName);

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

    /**
     * 修改密码，修改成功自动清空文件中数据
     * @param oldPass 旧密码
     * @param newPass 新密码
     */
    public void changePass(String oldPass,String newPass){
        if(dao!=null&&context!=null&&view!=null){
            SharedPreferences preferences = dao.readUserInfo(context);
            String userid = preferences.getString("userId", "");

            if (userid==""){
                Log.i("test", "文件中没有用户id或用户名称");
                return;
            }
            LinkedHashMap<String,String> map=new LinkedHashMap<>();
            map.put("userID",userid);
            map.put("oldPass",oldPass);
            map.put("newPass",newPass);

            dao.changePass(context, map, new ToolsDao.ChangePassListener() {
                @Override
                public void result(boolean isChange) {
                    if (isChange){
                        dao.saveUserInfo(context,null,null,null);
                    }
                    view.isChangePass(isChange);
                }
            });
        }
    }

    public void saveUserInfo(String userName,String pwd,UserLoginBean bean){
        dao.saveUserInfo(context,userName,pwd,bean);
    }
    public SharedPreferences readUserInfo(){
        return dao.readUserInfo(context);
    }

}
