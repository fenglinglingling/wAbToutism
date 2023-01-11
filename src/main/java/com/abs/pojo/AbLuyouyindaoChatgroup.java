package com.abs.pojo;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AbLuyouyindaoChatgroup)实体类
 *
 * @author makejava
 * @since 2022-11-08 19:56:07
 */
@Data
public class AbLuyouyindaoChatgroup implements Serializable {
    private static final long serialVersionUID = 384141082789874997L;
    /**
     * 聊天群号
     */
    private Integer groupId;
    /**
     * 聊天群名
     */
    private String groupName;
    /**
     * 创建人号
     */
    private String userId;
    /**
     * 创建时间
     */
    private Date time;
    /**
     * 群头像
     */
    private String img;
    /**
     * 群介绍、标签等
     */
    private String type;


}

