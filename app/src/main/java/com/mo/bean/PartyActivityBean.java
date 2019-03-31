package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 */

public class PartyActivityBean {

    private List<ReplyListBean> replyList;
    private List<PartyActivitiesListBean> partyActivitiesList;

    /**
     * 获取回复列表
     * @return
     */
    public List<ReplyListBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyListBean> replyList) {
        this.replyList = replyList;
    }

    /**
     * 获取活动列表
     * @return
     */
    public List<PartyActivitiesListBean> getPartyActivitiesList() {
        return partyActivitiesList;
    }

    public void setPartyActivitiesList(List<PartyActivitiesListBean> partyActivitiesList) {
        this.partyActivitiesList = partyActivitiesList;
    }

    public static class ReplyListBean {
        /**
         * id : 4
         * userID : 1
         * userName : 孙华林
         * replyContent : sdfsd sfdsdsdf
         */

        private String id;
        private String userID;
        private String userName;
        private String replyContent;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }
    }

    public static class PartyActivitiesListBean {
        /**
         * imgUrl : cc.jpg
         * typeName : 党务活动
         * id : 3
         * title : 党务活动发布测试1
         * writerPersonName : 孙华林
         * yxx : 1
         * createDate : 2019-03-16
         * writerID : 1
         * workTask : 党务活动发布测试1党务活动发布测试1党务活动发布测试1党务活动发布测试1党务活动发布测试1党务活动发布测试1
         党务活动发布测试1党务活动发布测试1
         党务活动发布测试1
         党务活动发布测试1
         * typeID : 2
         */

        private String imgUrl;
        private String typeName;
        private String id;
        private String title;
        private String writerPersonName;
        private String yxx;
        private String createDate;
        private String writerID;
        private String workTask;
        private String typeID;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWriterPersonName() {
            return writerPersonName;
        }

        public void setWriterPersonName(String writerPersonName) {
            this.writerPersonName = writerPersonName;
        }

        public String getYxx() {
            return yxx;
        }

        public void setYxx(String yxx) {
            this.yxx = yxx;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getWriterID() {
            return writerID;
        }

        public void setWriterID(String writerID) {
            this.writerID = writerID;
        }

        public String getWorkTask() {
            return workTask;
        }

        public void setWorkTask(String workTask) {
            this.workTask = workTask;
        }

        public String getTypeID() {
            return typeID;
        }

        public void setTypeID(String typeID) {
            this.typeID = typeID;
        }
    }
}
