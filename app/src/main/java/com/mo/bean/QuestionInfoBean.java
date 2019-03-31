package com.mo.bean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/27.
 */

public class QuestionInfoBean {

    /**
     * problemList : [{"id":"1","title":"1+1=？","selectA":"1","selectC":"3","selectB":"2","answer":"A","selectD":"4"},{"id":"2","title":"2+2=?","selectA":"1","selectC":"3","selectB":"2","answer":"D","selectD":"4"}]
     * msg : success
     */

    private String msg;
    private List<ProblemListBean> problemList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ProblemListBean> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<ProblemListBean> problemList) {
        this.problemList = problemList;
    }

    public static class ProblemListBean {
        /**
         * id : 1
         * title : 1+1=？
         * selectA : 1
         * selectC : 3
         * selectB : 2
         * answer : A
         * selectD : 4
         */

        private String id;
        private String title;
        private String selectA;
        private String selectC;
        private String selectB;
        private String answer;
        private String selectD;

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

        public String getSelectA() {
            return selectA;
        }

        public void setSelectA(String selectA) {
            this.selectA = selectA;
        }

        public String getSelectC() {
            return selectC;
        }

        public void setSelectC(String selectC) {
            this.selectC = selectC;
        }

        public String getSelectB() {
            return selectB;
        }

        public void setSelectB(String selectB) {
            this.selectB = selectB;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getSelectD() {
            return selectD;
        }

        public void setSelectD(String selectD) {
            this.selectD = selectD;
        }
    }
}
