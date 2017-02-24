package com.swjtu.cn.bean;

public class Admin {
    private Integer id;

    private String name;

    private String loginName;

    private String password;

    private Integer competence;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getCompetence() {
        return competence;
    }

    public void setCompetence(Integer competence) {
        this.competence = competence;
    }
}