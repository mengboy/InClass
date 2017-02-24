package com.swjtu.cn.bean;

import com.swjtu.cn.utils.BasicUser;

public class Student implements BasicUser{
    private Integer studentNumber;

    private String password;

    private Integer islogin;

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getIslogin() {
        return islogin;
    }

    public void setIslogin(Integer islogin) {
        this.islogin = islogin;
    }
}