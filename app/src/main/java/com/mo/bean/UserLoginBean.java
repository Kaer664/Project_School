package com.mo.bean;

/**
 * Created by 风雨诺 on 2019/3/26.
 */

public class UserLoginBean {

    /**
     * userID : 1
     * userRealName : 孙华林
     * msg : loginSuccess
     */

    private String userID;
    private String userRealName;
    private String msg;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
