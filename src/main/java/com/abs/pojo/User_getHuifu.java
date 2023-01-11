package com.abs.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class User_getHuifu { // 获取 评论 以及其回复list
    private List<AbLuyoutaolunHuifu> HuifuList;//对评论的回复
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

}
