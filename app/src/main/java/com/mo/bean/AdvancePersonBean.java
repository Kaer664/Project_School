package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 */

public class AdvancePersonBean {

    /**
     * advancedPersonList : [{"imgUrl":"IMG_20160220_111800.jpg","typeName":"先进人物","id":"5","title":"刘胡兰","writerPersonName":"孙华林","yxx":"1","createDate":"2019-03-16","writerID":"1","typeID":"3","workTask":"刘胡兰刘胡兰刘胡兰刘胡兰刘胡兰刘胡兰刘胡兰刘胡兰"}]
     * msg : success
     */

    private String msg;
    private List<AdvancedPersonListBean> advancedPersonList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<AdvancedPersonListBean> getAdvancedPersonList() {
        return advancedPersonList;
    }

    public void setAdvancedPersonList(List<AdvancedPersonListBean> advancedPersonList) {
        this.advancedPersonList = advancedPersonList;
    }

    public static class AdvancedPersonListBean {
        /**
         * imgUrl : IMG_20160220_111800.jpg
         * typeName : 先进人物
         * id : 5
         * title : 刘胡兰
         * writerPersonName : 孙华林
         * yxx : 1
         * createDate : 2019-03-16
         * writerID : 1
         * typeID : 3
         * workTask : 刘胡兰刘胡兰刘胡兰刘胡兰刘胡兰刘胡兰刘胡兰刘胡兰
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

        @Override
        public String toString() {
            return "AdvancedPersonListBean{" +
                    "imgUrl='" + imgUrl + '\'' +
                    ", typeName='" + typeName + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", writerPersonName='" + writerPersonName + '\'' +
                    ", yxx='" + yxx + '\'' +
                    ", createDate='" + createDate + '\'' +
                    ", writerID='" + writerID + '\'' +
                    ", typeID='" + typeID + '\'' +
                    ", workTask='" + workTask + '\'' +
                    '}';
        }

        public void setWorkTask(String workTask) {
            this.workTask = workTask;
        }
    }
}
