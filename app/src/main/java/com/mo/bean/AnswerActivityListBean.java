package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 */

public class AnswerActivityListBean {

    /**
     * userAnswerActivityList : [{"startTime":"2019-03-28","id":"2","createTime":"2019-03-18","title":"竞赛2","yesOrNotDo":"0","selectedProblemList":"1,2,3,","yxx":"1","endTime":"2019-04-04"},{"startTime":"2019-03-19","id":"1","userID":"1","createTime":"2019-03-18","title":"竞赛1","yesOrNotDo":"1","selectedProblemList":"1,2,","score":"8","userName":"孙华林","yxx":"1","endTime":"2019-03-21"}]
     * msg : success
     */

    private String msg;
    private List<UserAnswerActivityListBean> userAnswerActivityList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UserAnswerActivityListBean> getUserAnswerActivityList() {
        return userAnswerActivityList;
    }

    public void setUserAnswerActivityList(List<UserAnswerActivityListBean> userAnswerActivityList) {
        this.userAnswerActivityList = userAnswerActivityList;
    }

    public static class UserAnswerActivityListBean {
        /**
         * startTime : 2019-03-28
         * id : 2
         * createTime : 2019-03-18
         * title : 竞赛2
         * yesOrNotDo : 0
         * selectedProblemList : 1,2,3,
         * yxx : 1
         * endTime : 2019-04-04
         * userID : 1
         * score : 8
         * userName : 孙华林
         */

        private String startTime;
        private String id;
        private String createTime;
        private String title;
        private String yesOrNotDo;
        private String selectedProblemList;
        private String yxx;
        private String endTime;
        private String userID;
        private String score;
        private String userName;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYesOrNotDo() {
            return yesOrNotDo;
        }

        public void setYesOrNotDo(String yesOrNotDo) {
            this.yesOrNotDo = yesOrNotDo;
        }

        public String getSelectedProblemList() {
            return selectedProblemList;
        }

        public void setSelectedProblemList(String selectedProblemList) {
            this.selectedProblemList = selectedProblemList;
        }

        public String getYxx() {
            return yxx;
        }

        public void setYxx(String yxx) {
            this.yxx = yxx;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

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
