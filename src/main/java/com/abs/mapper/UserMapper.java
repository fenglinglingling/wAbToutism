package com.abs.mapper;

import com.abs.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@Mapper
public interface UserMapper {

    User getUserByEmail(String user_email);

    /**
     * 用户注册
     *
     * @param user User对象
     * @return true注册成功
     */
    boolean register(User user);
    /**
     * 获取用户信息 byId
     *
     * @param user_id String
     * @return UserController 对象
     */
    User getUserById(String user_id);


    /**
     * 获取用户信息 By phone
     *
     * @param user_phone String
     * @return UserController 对象
     */
    User getUserByPhone(String user_phone);

    /**
     * 获取所有用户信息
     *
     * @param
     * @return UserController 对象
     */
    List<User> getAllUser();

    /**
     * 修改账户 各种信息（手机号 密码 头像 昵称。。。。） 不是空的才更新
     *
     * @param user 对象
     * @return
     */
    boolean UpdateUser(User user);


    /**
     * 注销用户（删除用户）
     *
     * @param user_id
     * @return true删除成功
     */
    boolean DeleteUserById(String user_id);


    /** 通过user_id 住宿记录表查询 住宿店铺id 在查询出店铺详情List
     * getUserStaysById  我的住宿记录表
     *  getZhusuByZhusu_id 通过zhusu_id查询住宿店铺
     @param user_id
     *@return 最后返回我的住宿表
     */
    List<User_zhusu> getUserStaysById(@Param("user_id") String user_id, @Param("Fenye")Map<String,Integer> Fenye );
    //通过住宿店 zhusu_id 查找该店的所有信息
    List<AbZhusuZhusu>getZhusuByZhusu_id(Integer id);



    /**
     * 我的路线（我的行程）
     *
     * @param user_id
     * @return
     */
    List<AbLuyouyindaoLuxian> getUserLineById(@Param("user_id")String user_id,@Param("Fenye")Map<String,Integer> Fenye);


    /**  （已经排序了） 最近日期的在最前面
     * 查找我的话题List（我发布的）
     * @param user_id
     */
    List<AbLuyoutaolunHuati> getUserTopicById(@Param("user_id")String user_id,@Param("Fenye")Map<String,Integer> Fenye);


    /**查找
     *话题评论
     *通过user_id 找到话题id（话题表） 然后 通过话题id 找到评论Pinlun List（评论表）
     * @param user_id
     * @return 话题评论 话题内容详情(前端需要啥 自己取出来就行) :Topic ;评论表List;评论人详情
     */
    List<User_getPinlun> getUserPinglunUserByUserId(@Param("user_id")String user_id,@Param("Fenye")Map<String,Integer> Fenye);
    //根据话题id查找评论
    List<AbLuyoutaolunComment>getTopicPinglunByTopicId(Integer Topic_id);



    /**查找
     * 评论回复
     *通过user_id 找到评论id（评论表） 然后 通过评论id 找到 huifu List（回复表）
     * @param user_id
     * @return 我的该条评论 (前端需要啥 自己取出来就行):PinLun ;回复表List;
     */
    List<User_getHuifu>getUserHuifuByUserId(@Param("user_id")String user_id,@Param("Fenye")Map<String,Integer> Fenye);
    //根据 评论id找回复
    List<AbLuyoutaolunHuifu>getPinglunHuifuByPinlunId(Integer pinlun_id);

//    /** 获取 点赞用户（话题 评论 回复的）的内容
//     *      暂未实现
//     * @param user_id
//     */
//     List<User_ToUserLike>getToUserLike(String user_id);

    /**
     * 删除用户的 话题 评论 回复
     * @param user_id 用户id
     *@param who 删除什么 话题（0） 评论（1）  回复（2）
    * @param id (评论,回复)
     * @return
     */
    boolean DeleteUser_PinglunOrhuifuById(@Param("user_id") String user_id,@Param("who") String who,@Param("id") Integer id);


}



