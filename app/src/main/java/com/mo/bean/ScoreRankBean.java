package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 * 实体类
 * 积分排行
 */

public class ScoreRankBean {

    private List<AllUserScoreListBean> allUserScoreList;

    public List<AllUserScoreListBean> getAllUserScoreList() {
        return allUserScoreList;
    }

    public void setAllUserScoreList(List<AllUserScoreListBean> allUserScoreList) {
        this.allUserScoreList = allUserScoreList;
    }

    public static class AllUserScoreListBean {
        /**
         * userID : 1
         * score : 46
         * userName : 孙华林
         */

        private String userID;
        private String score;
        private String userName;

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
