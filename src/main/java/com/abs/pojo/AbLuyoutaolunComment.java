package com.abs.pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * (AbLuyoutaolunComment)实体类
 *
 * @author makejava
 * @since 2022-11-07 20:36:14
 */
public class AbLuyoutaolunComment implements Serializable {
    private static final long serialVersionUID = -56122085956654420L;
    /**
     * 评论号
     */
    private Integer commentsId;
    /**
     * 话题号
     */
    private Integer topicId;
    /**
     * 评论人的id
     */
    private String userId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 点赞量
     */
    private Integer like;
    
    private Date time;


    public Integer getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(Integer commentsId) {
        this.commentsId = commentsId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
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

}

