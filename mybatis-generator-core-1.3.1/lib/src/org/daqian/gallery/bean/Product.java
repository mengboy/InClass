package org.daqian.gallery.bean;

import java.util.Date;

public class Product {
    private Integer id;

    private String name;

    private Integer typeId;

    private Integer artistId;

    private String picAddr;

    private Float price;

    private String size;

    private String time;

    private String mark;

    private String texture;

    private String remark;

    private Date uploadtime;

    private Integer newornot;

    private String inscriptions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getPicAddr() {
        return picAddr;
    }

    public void setPicAddr(String picAddr) {
        this.picAddr = picAddr == null ? null : picAddr.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture == null ? null : texture.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Integer getNewornot() {
        return newornot;
    }

    public void setNewornot(Integer newornot) {
        this.newornot = newornot;
    }

    public String getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(String inscriptions) {
        this.inscriptions = inscriptions == null ? null : inscriptions.trim();
    }
}