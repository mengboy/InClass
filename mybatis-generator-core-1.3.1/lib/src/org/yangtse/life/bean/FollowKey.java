package org.yangtse.life.bean;

public class FollowKey {
    private Integer userId;

    private Integer userfollowedId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserfollowedId() {
        return userfollowedId;
    }

    public void setUserfollowedId(Integer userfollowedId) {
        this.userfollowedId = userfollowedId;
    }
}