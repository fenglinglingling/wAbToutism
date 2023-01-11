package com.abs.mapper;

import com.abs.pojo.AbCultureVedio;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AbCultureVedioMapper {
    List<AbCultureVedio> getAllAbCulVedioList();
}

