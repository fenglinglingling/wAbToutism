package com.abs.pojo;

public class AbLuyoutaolunHuifuLike {
    private Integer id;
    private String userId;
    private Integer responseId;

    @Override
    public String toString() {
        return "AbLuyoutaolunHuifuLike{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", responseId=" + responseId +
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

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }
}
