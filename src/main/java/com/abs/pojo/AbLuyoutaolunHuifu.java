package com.abs.pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * (AbLuyoutaolunHuifu)实体类
 *
 * @author makejava
 * @since 2022-11-07 20:36:25
 */
public class AbLuyoutaolunHuifu implements Serializable {
    private static final long serialVersionUID = -16672352128924209L;
    /**
     * 回复号
     */
    private Integer responseId;
    /**
     * 评论号
     */
    private Integer commentsId;
    /**
     * 回复人的id
     */
    private String userId;
    /**
     * 回复内容
     */
    private String content;
    /**
     * 点赞量
     */
    private Integer like;
    
    private Date time;
    
    private Integer topicId;


    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
    }

    public Integer getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(Integer commentsId) {
        this.commentsId = commentsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

}

