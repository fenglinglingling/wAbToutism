package com.abs.mapper;

import com.abs.pojo.AbZhusuOrders;
import com.abs.pojo.AbZhusuZhusu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ZhusuMapper {

   boolean delete(Integer id);
   boolean update(AbZhusuZhusu zhusu);
   boolean sava(AbZhusuZhusu zhusu);
   AbZhusuZhusu getZhusuId(Integer id);

  public List<AbZhusuZhusu>  selectZhuSuByPage(Integer page, Integer limit, String search);





    /**
     * 得到住宿列表
     * @return
     */
    public List<AbZhusuZhusu> selectAllZhuSus();

    /**
     * 通过住宿名进行查询
     */
    public AbZhusuZhusu  selectByName(String name);

    /**
     * 通过住宿的id进行查询
     * @param id
     * @return
     */
    public AbZhusuZhusu  selectById(Integer id);

    /**
     * 通过住宿的类型进行查询
     * @param type
     * @return
     */
    public List<AbZhusuZhusu> selectByType(String type);

    /**
     * 通过热度进行降序排序
     * @param
     * @return
     */
    public List<AbZhusuZhusu> selectByHot();

    /**
     * 首页住宿 4 条
     * @param
     * @return
     */
    public List<AbZhusuZhusu> selectFourByHot();


    /**
     * 查询某个商店(id)最近住宿记录
     * @return
     */
    public List<AbZhusuOrders> selectOrders(int id);

    /**
     * 手动添加购房记录
     * @param orders
     * @return
     */
    public boolean addOrders(AbZhusuOrders orders);

}
