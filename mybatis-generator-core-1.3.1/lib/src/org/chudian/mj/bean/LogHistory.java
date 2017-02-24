package org.chudian.mj.bean;

import java.util.Date;

public class LogHistory {
    private Integer id;

    private Integer userId;

    private String operationType;

    private Date operatonTime;

    private String operationContent;

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

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    public Date getOperatonTime() {
        return operatonTime;
    }

    public void setOperatonTime(Date operatonTime) {
        this.operatonTime = operatonTime;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent == null ? null : operationContent.trim();
    }
}