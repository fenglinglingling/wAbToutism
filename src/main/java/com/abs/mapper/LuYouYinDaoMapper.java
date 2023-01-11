package com.abs.mapper;

import com.abs.pojo.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LuYouYinDaoMapper {
    /**
     * 查询所有的景区
     * @return
     */
    public List<AbLuyouyindaoJingqu> selectAllJingQus();


     //通过用户输入的部分信息推荐路线
//    public  List<AbLuyouyindaoLuxian> privateLuXian(AbLuyouyindaoLuxian luxian);

    /**
     * 通过景点标签进行筛选
     * @param conditions
     * @return
     */
    public List<AbLuyouyindaoJingqu> selectJingQusByConditions(String conditions);

    /**
     * 根据景区名进行查询
     * @param name
     * @return
     */
    public List<AbLuyouyindaoJingqu> selectJingQusByName(String name);

    /**
     * 根据景区id进行查询
     * @param id
     * @return
     */
    public List<AbLuyouyindaoJingqu> selectJingQusById(Integer id);

    /**
     * 添加一个聊天群(房间)
     * @param chatgroup
     * @return
     */
    public boolean addGroup(AbLuyouyindaoChatgroup chatgroup);

    /**
     * 添加一次聊天信息
     * @param chat
     * @return
     */
    public boolean addChat(AbLuyouyindaoChat chat);

    /**
     * 添加一次聊天联系
     * @param lianxi
     * @return
     */
    public boolean addLianxi(AbLuyouyindaoChatlianxi lianxi);

    /**
     * 查找最大的聊天chat_id
     * @return
     */
    Integer getMaxId();


    /**
     * 查看该聊天群的信息
     * @param GroupId
     * @return
     */
    public AbLuyouyindaoChatgroup selectChatGroup(Integer  GroupId);

    /**
     * 查找某个聊天群的所有信息并按时间升序排序(默认)
     * @param GroupId
     * @return
     */
    public List<AbLuyouyindaoChat> selectChatByGroupId(Integer GroupId);

    /**
     * 查询全部的群聊
     * @return
     */
    public List<AbLuyouyindaoChatgroup> selectAllGroups();

    /**
     * 通过用户id查询他的信息
     * @param id
     * @return
     */
    public AbUser selectUserById(String id);

    /**
     * 根据id查询群聊
     * @param id
     * @return
     */
    public AbLuyouyindaoChatgroup selectGroupById(Integer id);

    public List<AbLuyouyindaoLuxian> selectAllLuXian(@Param("begin") int begin, @Param("pageSize")int pageSize,@Param("userId")String userId);

    public int selectCount();

    public boolean deleteById(int id);


}
