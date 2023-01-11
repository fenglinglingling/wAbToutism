package com.abs.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User_message {
    private Integer id;
    private String  userId;
    private String  userQuestion;
    private String  manager_answer;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date replyTime;
    private Integer status;

    public User_message() {
    }

    public User_message(Integer id, String userId, String userQuestion, String manager_answer, Date createtime, Date replyTime, Integer status) {
        this.id = id;
        this.userId = userId;
        this.userQuestion = userQuestion;
        this.manager_answer = manager_answer;
        this.createtime = createtime;
        this.replyTime = replyTime;
        this.status = status;
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

    public String getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(String userQuestion) {
        this.userQuestion = userQuestion;
    }

    public String getManager_answer() {
        return manager_answer;
    }

    public void setManager_answer(String manager_answer) {
        this.manager_answer = manager_answer;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User_message{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userQuestion='" + userQuestion + '\'' +
                ", manager_answer='" + manager_answer + '\'' +
                ", createtime=" + createtime +
                ", replyTime=" + replyTime +
                ", status=" + status +
                '}';
    }
}
