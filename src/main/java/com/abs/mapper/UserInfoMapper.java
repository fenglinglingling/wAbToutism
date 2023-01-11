package com.abs.mapper;

import com.abs.pojo.AbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
//仅供参考，可以删除！！！！！！！！！！！！！！！！！！！！！！！！

@Repository
@Mapper
public interface UserInfoMapper {
    //查询所有的用户信息
    public List<AbUser> getAllUser();
    //通过用户名模糊查询用户信息
    public List<AbUser> getAllUserByUserName(String atr);

    //通过用户id删除用户信息
    public boolean deleteUser(String atr);
//    ============================================
    //通过layui得到分页数据列表
    List<AbUser> getUserByPage(@Param("page") int page, @Param("pageSize") int pageSize);
    //得到分页的总数居
    int countUserNumber();
    //通过用户id添加用户信息
    public boolean addUser(AbUser abUser);
    //通过用户id修改用户信息
    public boolean editUser(AbUser abUser);
    //用户通过用户id修改信息
    public boolean editUserGeRenInfo(AbUser abUser);
    //通过用户id修改用户信息
    public AbUser findUpdateUser(String id);
    //得到搜索后的分页数据列表
    List<AbUser> getUserByPageByKey(@Param("page") int page,@Param("pageSize") int pageSize,@Param("search_key") String search_key);
   //修改头像
    public boolean editUserImg(@Param("id") String id, @Param("imgName") String imgName);
    //得到通过条件查询的结果的总数居
    int countUserByPageByKey();
}
