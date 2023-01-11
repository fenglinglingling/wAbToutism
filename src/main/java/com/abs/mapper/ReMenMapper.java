package com.abs.mapper;

import com.abs.pojo.remen_diqu;
import com.abs.pojo.shouye_diqu;
import com.abs.pojo.shouye_zixun;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ReMenMapper {

    List<remen_diqu> getAllDiQu();//地区

    List<remen_diqu> getSixDiQu();//6条热门地区

    String getIntroductionById(int id);

}
