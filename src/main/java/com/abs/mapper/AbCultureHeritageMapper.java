package com.abs.mapper;


import com.abs.pojo.AbCultureHeritage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface AbCultureHeritageMapper {
    //查询非遗文化得全部列表
    List<AbCultureHeritage> getAllCulHeritageList();
    //    通过名字查找文化活动信息
    AbCultureHeritage AbHerigateInfoById(int id);
}
