package com.abs.mapper;

import com.abs.pojo.Diqu;
import com.abs.pojo.remen_diqu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DiquMapper {
    public List<Diqu> queryAllDiqu();
    public List<Diqu> queryOneDiquByName(String Name);

    //后台
    public boolean insertDiQu(remen_diqu diqu);
    public boolean delDiQu(Integer id);
    public boolean updateDiQu(remen_diqu diqu);
    public List<remen_diqu> selectDiQuByPage(@Param("begin") int begin, @Param("pageSize")int pageSize);
    public int getCount();
    public remen_diqu selectDiQuById(Integer id);
    public List<remen_diqu> selectDiQuByCondition(String condition);
    public int getCountByCondition(String condition);
}
