package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 */

public class LearningGardenInfoBean {

    private List<ReplyListBean> replyList;
    private List<LearningGardenListBean> learningGardenList;

    public List<ReplyListBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyListBean> replyList) {
        this.replyList = replyList;
    }

    public List<LearningGardenListBean> getLearningGardenList() {
        return learningGardenList;
    }

    public void setLearningGardenList(List<LearningGardenListBean> learningGardenList) {
        this.learningGardenList = learningGardenList;
    }

    public static class ReplyListBean {
        /**
         * id : 6
         * userID : 1
         * userName : 孙华林
         * replyContent : 我要好好学习
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

    public static class LearningGardenListBean {
        /**
         * imgUrl : IMG_20160220_111800.jpg
         * typeName : 学习园地
         * id : 7
         * writerPersonName : 孙华林
         * title : 学习1
         * videoUrl : 画中画.mp4
         * createDate : 2019-03-15
         * yxx : 1
         * writerID : 1
         * typeID : 4
         * fileUrl : 仇彬简历1.doc
         * workTask : 学习1学习1学习1学习1学习1
         */

        private String imgUrl;
        private String typeName;
        private String id;
        private String writerPersonName;
        private String title;
        private String videoUrl;
        private String createDate;
        private String yxx;
        private String writerID;
        private String typeID;
        private String fileUrl;
        private String workTask;

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

        public String getWriterPersonName() {
            return writerPersonName;
        }

        public void setWriterPersonName(String writerPersonName) {
            this.writerPersonName = writerPersonName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getYxx() {
            return yxx;
        }

        public void setYxx(String yxx) {
            this.yxx = yxx;
        }

        public String getWriterID() {
            return writerID;
        }

        public void setWriterID(String writerID) {
            this.writerID = writerID;
        }

        public String getTypeID() {
            return typeID;
        }

        public void setTypeID(String typeID) {
            this.typeID = typeID;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getWorkTask() {
            return workTask;
        }

        public void setWorkTask(String workTask) {
            this.workTask = workTask;
        }
    }
}
