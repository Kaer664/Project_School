package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/26.
 */

public class PartyNewsListBean {

    /**
     * partyAffairsNewsList : [{"imgUrl":"cc.jpg","typeName":"党务信息","id":"1","writerPersonName":"孙华林","title":"测试1","createDate":"2019-03-16","yxx":"1","writerID":"1","typeID":"1","workTask":"测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1"},{"imgUrl":"cc.jpg","typeName":"党务信息","id":"2","writerPersonName":"孙华林","title":"测试2","createDate":"2019-03-16","yxx":"1","writerID":"1","typeID":"1","workTask":"测试2测试2测试2测试2"}]
     * msg : success
     */

    private String msg;
    private List<PartyAffairsNewsListBean> partyAffairsNewsList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PartyAffairsNewsListBean> getPartyAffairsNewsList() {
        return partyAffairsNewsList;
    }

    public void setPartyAffairsNewsList(List<PartyAffairsNewsListBean> partyAffairsNewsList) {
        this.partyAffairsNewsList = partyAffairsNewsList;
    }

    public static class PartyAffairsNewsListBean {
        /**
         * imgUrl : cc.jpg
         * typeName : 党务信息
         * id : 1
         * writerPersonName : 孙华林
         * title : 测试1
         * createDate : 2019-03-16
         * yxx : 1
         * writerID : 1
         * typeID : 1
         * workTask : 测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1测试1
         */

        private String imgUrl;
        private String typeName;
        private String id;
        private String writerPersonName;
        private String title;
        private String createDate;
        private String yxx;
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

        public String getWorkTask() {
            return workTask;
        }

        public void setWorkTask(String workTask) {
            this.workTask = workTask;
        }
    }
}
