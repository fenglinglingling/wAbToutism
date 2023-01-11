package com.abs.pojo;

import java.io.Serializable;

/**
 * (AbZixunReply)实体类
 *
 * @author makejava
 * @since 2022-11-08 19:12:36
 */
public class AbZixunReply implements Serializable {
    private static final long serialVersionUID = -80044758988141527L;
    
    private Integer replyId;
    
    private Integer questionId;
    
    private String replyContent;


    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

}

