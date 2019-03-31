package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 */

public class PartyActivityListBean {

    /**
     * partyActivitiesList : [{"imgUrl":"cc.jpg","typeName":"党务活动","id":"3","title":"党务活动发布测试1","writerPersonName":"孙华林","yxx":"1","createDate":"2019-03-16","writerID":"1","typeID":"2","workTask":"党务活动发布测试1党务活动发布测试1党务活动发布测试1党务活动发布测试1党务活动发布测试1党务活动发布测试1\r\n党务活动发布测试1党务活动发布测试1\r\n党务活动发布测试1\r\n党务活动发布测试1"},{"imgUrl":"cc.jpg","typeName":"党务活动","id":"4","title":"党务活动发布测试2","writerPersonName":"孙华林","yxx":"1","createDate":"2019-03-16","writerID":"1","typeID":"2","workTask":"党务活动发布测试2\r\n党务活动发布测试2党务活动发布测试2\r\n党务活动发布测试2\r\n\r\n党务活动发布测试2\r\n党务活动发布测试2\r\n党务活动发布测试2"}]
     * msg : success
     */

    private String msg;
    private List<PartyActivitiesListBean> partyActivitiesList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PartyActivitiesListBean> getPartyActivitiesList() {
        return partyActivitiesList;
    }

    public void setPartyActivitiesList(List<PartyActivitiesListBean> partyActivitiesList) {
        this.partyActivitiesList = partyActivitiesList;
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
         * typeID : 2
         * workTask : 党务活动发布测试1党务活动发布测试1党务活动发布测试1党务活动发布测试1党务活动发布测试1党务活动发布测试1
         党务活动发布测试1党务活动发布测试1
         党务活动发布测试1
         党务活动发布测试1
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
