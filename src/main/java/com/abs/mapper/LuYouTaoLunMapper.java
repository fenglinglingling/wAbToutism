package com.abs.mapper;

import com.abs.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface LuYouTaoLunMapper {
    /**
     * 查询所有的话题按时间进行降序(默认)
     * @return
     */
    public ArrayList<AbLuyoutaolunHuati> selectHuaTis();

    /**
     * 查询所有的话题按时间进行降序(默认)
     * @return
     */
    public ArrayList<AbLuyoutaolunHuati> selectAllHuaTisOrderBytopicId();

    /**
     * 查询话题发布者的信息~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * @return
     */
    public AbUser selectHuaTisFromUser(String id);

    /**
     * 通过话题的id来进行查询
     * @param id
     * @return
     */
    public AbLuyoutaolunHuati selectById(Integer id);

    /**
     * 根据话题的title来进行查询
     * @param title
     * @return
     */
    public List<AbLuyoutaolunHuati> selectByTitle(String title);

    /**
     * 通过话题的id来查询它的评论
     * @return
     */
    public List<AbLuyoutaolunComment> selectCommentByHuatiId(Integer topic_id);

    /**
     * 通过评论的id来查询出他的回复
     * @return
     */
    public List<AbLuyoutaolunHuifu> selectHuiFuByCommentId(Integer comments_id);
    /**
     * 通过评论的id来查询出他的评论信息
     * @return
     */
    public AbLuyoutaolunComment selectCommentByCommentId(Integer comments_id);
    /**
     * 通过回复的id来查询出他的回复信息
     * @return
     */
    public AbLuyoutaolunHuifu selectHuifuByResponseId(Integer response_id);

    /**
     * 添加一个话题
     * @param huati
     * @return
     */
    public boolean addHuati(AbLuyoutaolunHuati huati);

    /**
     * 添加一个话题评论
     * @param comment
     * @return
     */
    public boolean addComment(AbLuyoutaolunComment comment);

    /**
     * 添加一个评论回复
     * @param huifu
     * @return
     */
    public boolean addCommentHuiFu(AbLuyoutaolunHuifu huifu);
    /**
     * 给一个话题添加一个点赞
     */
    public boolean addHuaTiLike(String userId,Integer topicId);
    public Integer queryHuaTilike(Integer topicId);
    public Integer queryHuaTilike1(String userId,Integer topicId);
    public boolean deleteHuaTilike(String userId,Integer topicId);
    /**
     * 给一个评论进行一个点赞
     */
    public boolean addCommentLike(String userId,Integer commentId);
    public Integer queryCommentlike(Integer commentId);
    public Integer queryCommentlike1(String userId,Integer commentId);
    public boolean deleteCommentlike(String userId,Integer commentId);


    /**
     * 给一个回复进行一个点赞
     */
    public boolean addHuiFuLike(String userId,Integer responseId);
    public Integer queryResponselike(Integer responseId);
    public Integer queryResponselike1(String userId,Integer responseId);
    public boolean deleteResponselike(String userId,Integer responseId);

    /**
     * 根据话题id添加一次浏览次数
     * @param topic_id
     * @return
     */
    public boolean addHuatiRead(Integer topic_id);


    /**  user
     * 根据userId查询话题
     * @param
     * @return
     */
    public List<AbLuyoutaolunHuati> getMyHuatiOrderTime(String userId);

    /**
     * 添加浏览记录
     * @param
     * @return
     */
    public boolean addHuatiRecord(AbluyoutaolunRecord record);
    /**
     * 删除一条浏览记录
     * @param
     * @return
     */
    public boolean deleteHuatiRecord(AbluyoutaolunRecord record);
    /**
     * 通过用户id查询浏览记录
     * @param
     * @return
     */
    public List<AbluyoutaolunRecord> querryHuatiRecord(String userId);

}
