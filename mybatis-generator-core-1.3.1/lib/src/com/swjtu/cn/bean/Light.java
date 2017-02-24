package com.swjtu.cn.bean;

import java.util.Date;

public class Light {
    private Integer id;

    private Integer status;

    private Integer changedstatus;

    private Integer changedmode;

    private Integer mode;

    private Integer statusischange;

    private Integer modeischange;

    private Integer statuschangeissuccess;

    private Integer modechangeissuccess;

    private Date time;

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

    public Integer getChangedstatus() {
        return changedstatus;
    }

    public void setChangedstatus(Integer changedstatus) {
        this.changedstatus = changedstatus;
    }

    public Integer getChangedmode() {
        return changedmode;
    }

    public void setChangedmode(Integer changedmode) {
        this.changedmode = changedmode;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getStatusischange() {
        return statusischange;
    }

    public void setStatusischange(Integer statusischange) {
        this.statusischange = statusischange;
    }

    public Integer getModeischange() {
        return modeischange;
    }

    public void setModeischange(Integer modeischange) {
        this.modeischange = modeischange;
    }

    public Integer getStatuschangeissuccess() {
        return statuschangeissuccess;
    }

    public void setStatuschangeissuccess(Integer statuschangeissuccess) {
        this.statuschangeissuccess = statuschangeissuccess;
    }

    public Integer getModechangeissuccess() {
        return modechangeissuccess;
    }

    public void setModechangeissuccess(Integer modechangeissuccess) {
        this.modechangeissuccess = modechangeissuccess;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}