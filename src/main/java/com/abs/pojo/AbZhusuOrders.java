package com.abs.pojo;

import java.io.Serializable;

/**
 * (AbZhusuOrders)实体类
 *
 * @author makejava
 * @since 2022-11-05 11:48:49
 */
public class AbZhusuOrders implements Serializable {
    private static final long serialVersionUID = -23973736207264595L;
    
    private Integer id;
    /**
     * 游客id
     */
    private String userid;
    
    private String zhusuId;
    
    private String time;

    @Override
    public String toString() {
        return "AbZhusuOrders{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", zhusuId='" + zhusuId + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getZhusuId() {
        return zhusuId;
    }

    public void setZhusuId(String zhusuId) {
        this.zhusuId = zhusuId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

