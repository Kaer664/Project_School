package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 * 实体类
 * 党员生日活动列表
 */

public class BirthActivityBean {

    /**
     * birthActivitiesList : [{"imgUrl":"IMG_20160220_111759.jpg","typeName":"党员生日活动","id":"8","title":"生日活动1","writerPersonName":"孙华林","yxx":"1","createDate":"2019-03-16","writerID":"1","typeID":"5","workTask":"生日活动1生日活动1生日活动1生日活动1生日活动1生日活动1生日活动1生日活动1生日活动1"},{"imgUrl":"IMG_20160220_111800.jpg","typeName":"党员生日活动","id":"9","title":"生日活动2","writerPersonName":"孙华林","yxx":"1","createDate":"2019-03-13","writerID":"1","typeID":"5","workTask":"生日活动2生日活动2生日活动2生日活动2"}]
     * msg : success
     */

    private String msg;
    private List<BirthActivitiesListBean> birthActivitiesList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BirthActivitiesListBean> getBirthActivitiesList() {
        return birthActivitiesList;
    }

    public void setBirthActivitiesList(List<BirthActivitiesListBean> birthActivitiesList) {
        this.birthActivitiesList = birthActivitiesList;
    }

    public static class BirthActivitiesListBean {
        /**
         * imgUrl : IMG_20160220_111759.jpg
         * typeName : 党员生日活动
         * id : 8
         * title : 生日活动1
         * writerPersonName : 孙华林
         * yxx : 1
         * createDate : 2019-03-16
         * writerID : 1
         * typeID : 5
         * workTask : 生日活动1生日活动1生日活动1生日活动1生日活动1生日活动1生日活动1生日活动1生日活动1
         */

        private String imgUrl;
        private String typeName;
        private String id;
        private String title;
        private String writerPersonName;
        private String yxx;
        private String createDate;
        private String writerID;
        private String typeID;
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

        public String getTypeID() {
            return typeID;
        }

        public void setTypeID(String typeID) {
            this.typeID = typeID;
        }

        public String getWorkTask() {
            return workTask;
        }

        public void setWorkTask(String workTask) {
            this.workTask = workTask;
        }
    }
}
