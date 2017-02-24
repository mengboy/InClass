package org.yangtse.life.bean;

import java.util.Date;

public class Help_comment {
    private Integer id;

    private Integer userId;

    private Integer helpId;

    private Date time;

    private String content;

    private Integer thanks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHelpId() {
        return helpId;
    }

    public void setHelpId(Integer helpId) {
        this.helpId = helpId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getThanks() {
        return thanks;
    }

    public void setThanks(Integer thanks) {
        this.thanks = thanks;
    }
}