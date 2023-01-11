package com.abs.pojo;

import lombok.Data;

@Data
public class meishi_food {
    private int id;
    private String name;
    private String introduction;
    private String introduction2;
    private String address;
    private String tujing;//品尝途径
    private String img;
    private String img2;
    private String shiping;//视频
    private int hotSort;//热门指数
}
