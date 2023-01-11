package com.abs.mapper;

import com.abs.pojo.AbLuyouyindaoJingqu;
import com.abs.pojo.AbZhusuZhusu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface JingquMapper {
    /**
     * 通过景区id列表查询对应景区的民宿列表
     */
    public List<AbLuyouyindaoJingqu> seleceZhuShuListByJqAllID();
}
