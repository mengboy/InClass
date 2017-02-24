package org.sist.schoolmate.bean;

import java.util.Date;

public class Donate {
    private Integer id;

    private String money;

    private String donator;

    private Date donateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getDonator() {
        return donator;
    }

    public void setDonator(String donator) {
        this.donator = donator == null ? null : donator.trim();
    }

    public Date getDonateTime() {
        return donateTime;
    }

    public void setDonateTime(Date donateTime) {
        this.donateTime = donateTime;
    }
}