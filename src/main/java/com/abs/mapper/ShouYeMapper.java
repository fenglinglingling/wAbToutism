package com.abs.mapper;

import com.abs.pojo.shouye_diqu;
import com.abs.pojo.shouye_zixun;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ShouYeMapper {

    List<shouye_zixun> getAllZiXun();//获取全部资讯

    String getContentById(int id);

    List<shouye_diqu> getAllDiQu();//地区

}
