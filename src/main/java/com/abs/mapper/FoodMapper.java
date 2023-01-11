package com.abs.mapper;

import com.abs.pojo.meishi_food;
import com.abs.pojo.remen_diqu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface FoodMapper {

    List<meishi_food> getAllFood();//FoodList
    List<meishi_food> getFourFood();//fourFood

    boolean zanOrCaiById(@Param("id")int id,@Param("count") int count);

}
