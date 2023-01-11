package com.abs.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 住宿记录表
 *
 * @author makejava
 * @since 2022-11-03 23:00:49
 */
@Data
public class User_zhusu {
    private Integer id;
    private String userid;
    private String zhusuId;
    private Date time;
    private List<AbZhusuZhusu> zhusuZhusuList;//用户住宿的 商家的详情list



}

