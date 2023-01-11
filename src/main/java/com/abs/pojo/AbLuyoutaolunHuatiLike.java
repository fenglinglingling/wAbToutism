package com.abs.pojo;

public class AbLuyoutaolunHuatiLike {
    private Integer id;
    private String userId;
    private Integer topicId;

    @Override
    public String toString() {
        return "AbLuyoutaolunHuatiLike{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", topicId=" + topicId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
}
