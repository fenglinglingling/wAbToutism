package com.abs.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class User_getPinlun {  // 获取该话题的 以及其评论list
    private List<AbLuyoutaolunComment> PinlunList; //该话题的评论表
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
}
