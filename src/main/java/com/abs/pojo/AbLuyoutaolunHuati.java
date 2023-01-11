package com.abs.pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * (AbLuyoutaolunHuati)实体类
 *
 * @author makejava
 * @since 2022-11-07 20:36:01
 */
public class AbLuyoutaolunHuati implements Serializable {
    private static final long serialVersionUID = -85443204319445282L;
    /**
     * 话题号
     */
    private Integer topicId;
    /**
     * 作者
     */
    private String userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 话题内容
     */
    private String introduction;
    /**
     * 发布时间
     */
    private Date time;
    /**
     * 点赞量
     */
    private Integer like;
    /**
     * 话题类型
     */
    private String type;
    /**
     * 地区
     */
    private String address;
    /**
     * 浏览量
     */
    private Integer read;

    @Override
    public String toString() {
        return "AbLuyoutaolunHuati{" +
                "topicId=" + topicId +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                ", time=" + time +
                ", like=" + like +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", read=" + read +
                '}';
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

}

