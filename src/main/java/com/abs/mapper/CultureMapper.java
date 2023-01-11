package com.abs.mapper;

import com.abs.pojo.culture_wenhua;
import com.abs.pojo.shouye_diqu;
import com.abs.pojo.shouye_zixun;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface CultureMapper {

    List<culture_wenhua> getAllCulture();//获取文化

    List<culture_wenhua> getFourCulture();//获取4文化


}
