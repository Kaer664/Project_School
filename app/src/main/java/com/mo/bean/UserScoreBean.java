package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 * 实体类
 * 用户积分信息
 */

public class UserScoreBean {

    /**
     * userAnswerActivityScorelist : [{"userID":"1","title":"竞赛1","score":"8","userName":"孙华林"},{"userID":"1","title":"ç«\u009eèµ\u009b2","score":"9","userName":"å­\u0099å\u008d\u008eæ\u009e\u0097"},{"userID":"1","title":"竞赛2","score":"9","userName":"孙华林"},{"userID":"1","title":"竞赛2","score":"9","userName":"孙华林"},{"userID":"1","title":"竞赛2","score":"9","userName":"孙华林"}]
     * userReplyScoreList : [{"userID":"1","title":"党务活动发布测试1","score":"1","userName":"孙华林"},{"userID":"1","title":"学习1","score":"1","userName":"孙华林"}]
     * answerActivityscoreSum : 44
     * scoreSum : 46
     * replyscoreSum : 2
     */

    private String answerActivityscoreSum;
    private String scoreSum;
    private String replyscoreSum;
    private List<UserAnswerActivityScorelistBean> userAnswerActivityScorelist;
    private List<UserReplyScoreListBean> userReplyScoreList;

    /**
     * 获取用户从注册开始，通过答题活动获得的积分
     * @return
     */
    public String getAnswerActivityscoreSum() {
        return answerActivityscoreSum;
    }

    public void setAnswerActivityscoreSum(String answerActivityscoreSum) {
        this.answerActivityscoreSum = answerActivityscoreSum;
    }

    /**
     * 用户获得的总积分
     * @return
     */
    public String getScoreSum() {
        return scoreSum;
    }

    public void setScoreSum(String scoreSum) {
        this.scoreSum = scoreSum;
    }

    /**
     * 从用户注册开始，通过回帖获得的积分
     * @return
     */
    public String getReplyscoreSum() {
        return replyscoreSum;
    }

    public void setReplyscoreSum(String replyscoreSum) {
        this.replyscoreSum = replyscoreSum;
    }

    /**
     * 获得答题活动情况列表
     * @return
     */
    public List<UserAnswerActivityScorelistBean> getUserAnswerActivityScorelist() {
        return userAnswerActivityScorelist;
    }

    public void setUserAnswerActivityScorelist(List<UserAnswerActivityScorelistBean> userAnswerActivityScorelist) {
        this.userAnswerActivityScorelist = userAnswerActivityScorelist;
    }

    /**
     * 获得回帖情况列表
     * @return
     */
    public List<UserReplyScoreListBean> getUserReplyScoreList() {
        return userReplyScoreList;
    }

    public void setUserReplyScoreList(List<UserReplyScoreListBean> userReplyScoreList) {
        this.userReplyScoreList = userReplyScoreList;
    }

    public static class UserAnswerActivityScorelistBean {
        /**
         * userID : 1
         * title : 竞赛1
         * score : 8
         * userName : 孙华林
         */

        private String userID;
        private String title;
        private String score;
        private String userName;

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

    public static class UserReplyScoreListBean {
        /**
         * userID : 1
         * title : 党务活动发布测试1
         * score : 1
         * userName : 孙华林
         */

        private String userID;
        private String title;
        private String score;
        private String userName;

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
