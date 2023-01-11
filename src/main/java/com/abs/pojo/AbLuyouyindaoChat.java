package com.abs.pojo;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AbLuyouyindaoChat)实体类
 *
 * @author makejava
 * @since 2022-11-08 19:55:55
 */
@Data
public class AbLuyouyindaoChat implements Serializable {
    private static final long serialVersionUID = -94631079945000515L;
    /**
     * 聊天号
     */
    private Integer chatId;
    /**
     * 聊天内容
     */
    private String content;
    /**
     * 用户号
     */
    private String userId;
    /**
     * 聊天时间
     */
    private Date time;


}

