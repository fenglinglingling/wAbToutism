package com.abs.pojo;


import java.io.Serializable;

/**
 * (AbZhusuZhusu)实体类
 *
 * @author makejava
 * @since 2022-11-05 12:05:10
 */
public class AbZhusuZhusu implements Serializable {
    private static final long serialVersionUID = 749128872612691418L;
    
    private Integer zhusuId;
    /**
     * 住宿名
     */
    private String zs_name;
    /**
     * 图片
     */
    private String zs_img;
    /**
     * 视频
     */
    private String zs_tv;
    /**
     * 热度点
     */
    private Integer zs_hot;
    /**
     * 住宿介绍（吃喝玩乐，文化）
     */
    private String zs_introduction;
    /**
     * 预定途径
     */
    private String zs_way;
    /**
     * 价格
     */
    private Double zs_price;
    /**
     * 地点
     */
    private String zs_address;
    /**
     * 类型（民俗or农家类）
     */
    private String zs_type;
    /**
     * 住宿电话
     */
    private String zs_phone;

    public AbZhusuZhusu() {
    }

    public AbZhusuZhusu(Integer zhusuId, String zs_name, String zs_img, String zs_tv, Integer zs_hot, String zs_introduction, String zs_way, Double zs_price, String zs_address, String zs_type, String zs_phone) {
        this.zhusuId = zhusuId;
        this.zs_name = zs_name;
        this.zs_img = zs_img;
        this.zs_tv = zs_tv;
        this.zs_hot = zs_hot;
        this.zs_introduction = zs_introduction;
        this.zs_way = zs_way;
        this.zs_price = zs_price;
        this.zs_address = zs_address;
        this.zs_type = zs_type;
        this.zs_phone = zs_phone;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getZhusuId() {
        return zhusuId;
    }

    public void setZhusuId(Integer zhusuId) {
        this.zhusuId = zhusuId;
    }

    public String getZs_name() {
        return zs_name;
    }

    public void setZs_name(String zs_name) {
        this.zs_name = zs_name;
    }

    public String getZs_img() {
        return zs_img;
    }

    public void setZs_img(String zs_img) {
        this.zs_img = zs_img;
    }

    public String getZs_tv() {
        return zs_tv;
    }

    public void setZs_tv(String zs_tv) {
        this.zs_tv = zs_tv;
    }

    public Integer getZs_hot() {
        return zs_hot;
    }

    public void setZs_hot(Integer zs_hot) {
        this.zs_hot = zs_hot;
    }

    public String getZs_introduction() {
        return zs_introduction;
    }

    public void setZs_introduction(String zs_introduction) {
        this.zs_introduction = zs_introduction;
    }

    public String getZs_way() {
        return zs_way;
    }

    public void setZs_way(String zs_way) {
        this.zs_way = zs_way;
    }

    public Double getZs_price() {
        return zs_price;
    }

    public void setZs_price(Double zs_price) {
        this.zs_price = zs_price;
    }

    public String getZs_address() {
        return zs_address;
    }

    public void setZs_address(String zs_address) {
        this.zs_address = zs_address;
    }

    public String getZs_type() {
        return zs_type;
    }

    public void setZs_type(String zs_type) {
        this.zs_type = zs_type;
    }

    public String getZs_phone() {
        return zs_phone;
    }

    public void setZs_phone(String zs_phone) {
        this.zs_phone = zs_phone;
    }

    @Override
    public String toString() {
        return "AbZhusuZhusu{" +
                "zhusuId=" + zhusuId +
                ", zs_name='" + zs_name + '\'' +
                ", zs_img='" + zs_img + '\'' +
                ", zs_tv='" + zs_tv + '\'' +
                ", zs_hot=" + zs_hot +
                ", zs_introduction='" + zs_introduction + '\'' +
                ", zs_way='" + zs_way + '\'' +
                ", zs_price=" + zs_price +
                ", zs_address='" + zs_address + '\'' +
                ", zs_type='" + zs_type + '\'' +
                ", zs_phone='" + zs_phone + '\'' +
                '}';
    }
}

