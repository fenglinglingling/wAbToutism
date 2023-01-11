package com.abs.mapper;

import com.abs.pojo.AbCultureFestival;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AbCultureFestivalMapper {
    //    通过名字查找文化节日信息
    AbCultureFestival AbFestivalInfoByName(String name);
}

