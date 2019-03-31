package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 * 当前月过生日的党员名单实体类
 */

public class BirthdayMonthBean {

    /**
     * userList : [{"name":"陶国正"},{"name":"秦桂英"},{"name":"徐登"}]
     * msg : success
     */

    private String msg;
    private List<UserListBean> userList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UserListBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListBean> userList) {
        this.userList = userList;
    }

    public static class UserListBean {
        /**
         * name : 陶国正
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
