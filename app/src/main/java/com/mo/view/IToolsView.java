package com.mo.view;

import com.mo.bean.UserLoginBean;

/**
 * Created by 风雨诺 on 2019/3/30.
 * 工具相关视图层接口
 */

public interface IToolsView {
    /**
     * 获取即使滚动通知
     * @param content
     */
    void showRollingNotify(String content);
    /**
     * 登陆成功返回的数据
     * @param bean
     */
    void showLogin(UserLoginBean bean);

    /**
     * 返回发表状态
     * @param b
     */
    void isReply(boolean b);

    /**
     * 返回反馈状态
     * @param b
     */
    void isFeedBack(boolean b);

    /**
     * 返回修改密码的状态
     * @param b
     */
    void isChangePass(boolean b);
}
