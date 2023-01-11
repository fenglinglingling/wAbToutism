package com.abs.mapper;

import com.abs.pojo.AbCultureActivity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AbCultureActivityMapper {
//    通过名字查找文化活动信息
    AbCultureActivity AbActivityInfoByName(String name);
}
