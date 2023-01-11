package com.abs.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * (AbLuyouyindaoJingqu)实体类
 *
 * @author makejava
 * @since 2022-11-08 19:54:58
 */
public class AbLuyouyindaoJingqu implements Serializable {
    private static final long serialVersionUID = -75971968850202802L;
    /**
     * 景区号
     */
    private Integer id;
    /**
     * 景区名
     */
    private String name;
    /**
     * 地点
     */
    private String address;
    /**
     * 视频
     */
    private String tv;
    /**
     * 图片
     */
    private String img;
    /**
     * 介绍
     */
    private String introduction;
    /**
     * 服务电话
     */
    private String phone;
    /**
     * 景区标签
     */
    private String type;

    //描述的是当前景区具备那些民宿
    private List<AbZhusuZhusu> abZhusuZhusuList;



    public List<AbZhusuZhusu> getAbZhusuZhusuList() {
        return abZhusuZhusuList;
    }

    public void setAbZhusuZhusuList(List<AbZhusuZhusu> abZhusuZhusuList) {
        this.abZhusuZhusuList = abZhusuZhusuList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "AbLuyouyindaoJingqu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tv='" + tv + '\'' +
                ", img='" + img + '\'' +
                ", introduction='" + introduction + '\'' +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                ", abZhusuZhusuList=" + abZhusuZhusuList +
                '}';
    }

}

