package com.abs.pojo;

import java.io.Serializable;

/**
 * (AbLuyouyindaoChatlianxi)实体类
 *
 * @author makejava
 * @since 2022-11-08 19:56:20
 */
public class AbLuyouyindaoChatlianxi implements Serializable {
    private static final long serialVersionUID = -51119258723534132L;
    /**
     * 群号与聊天的联系id
     */
    private Integer id;
    /**
     * 群号
     */
    private Integer groupId;
    /**
     * 聊天号
     */
    private Integer chatId;

    @Override
    public String toString() {
        return "AbLuyouyindaoChatlianxi{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", chatId=" + chatId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

}

