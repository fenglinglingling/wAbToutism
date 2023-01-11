package com.abs.pojo;

public class AbLuyoutaolunCommentLike {
    private Integer id;
    private String userId;
    private Integer commentId;

    @Override
    public String toString() {
        return "AbLuyoutaolunCommentLike{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", commentId=" + commentId +
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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}
