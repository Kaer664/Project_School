package com.example.lucky.myapplication.bean;

/**
 * Created by Lucky on 2019/4/8.
 */

public class GroupBean {
    private String title;
    private String score;

    public GroupBean(String title, String score) {
        this.title = title;
        this.score = score;
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
}
