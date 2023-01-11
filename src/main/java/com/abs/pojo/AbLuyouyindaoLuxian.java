package com.abs.pojo;

import java.io.Serializable;

/**
 * (AbLuyouyindaoLuxian)实体类
 *
 * @author makejava
 * @since 2022-11-08 19:55:34
 */
public class AbLuyouyindaoLuxian implements Serializable {
    private static final long serialVersionUID = 922591212774412267L;
    /**
     * 路线号
     */
    private Integer id;
    /**
     * 规划人
     */
    private String uerId;
    /**
     * 出行人
     */
    private String users;
    /**
     * 出行方式
     */
    private String way;
    /**
     * 路线标签（时间最短，路线最短）
     */
    private String type;
    /**
     * 预估费用
     */
    private Double price;
    /**
     * 出发日期
     */
    private String startTime;
    /**
     * 结束日期
     */
    private String endTime;
    /**
     * 出发地
     */
    private String startAddress;
    /**
     * 目的地
     */
    private String endAddress;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUerId() {
        return uerId;
    }

    public void setUerId(String uerId) {
        this.uerId = uerId;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

}

