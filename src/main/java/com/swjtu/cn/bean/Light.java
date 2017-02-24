package com.swjtu.cn.bean;

import java.util.Date;

public class Light {
    private Integer id;

    private Integer status;

    private Integer mode;

    private Integer changed;

    private Integer ischange;

    private Integer changeissuccess;

    private Date time;

    private String position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getChanged() {
        return changed;
    }

    public void setChanged(Integer changed) {
        this.changed = changed;
    }

    public Integer getIschange() {
        return ischange;
    }

    public void setIschange(Integer ischange) {
        this.ischange = ischange;
    }

    public Integer getChangeissuccess() {
        return changeissuccess;
    }

    public void setChangeissuccess(Integer changeissuccess) {
        this.changeissuccess = changeissuccess;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }
}