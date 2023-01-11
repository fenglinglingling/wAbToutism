package com.abs.mapper;

import com.abs.pojo.AbLuyoutaolunComment;
import com.abs.pojo.AbLuyoutaolunHuati;
import com.abs.pojo.AbLuyoutaolunHuifu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HuatiMapper {
    //查询所有话题
    public List<AbLuyoutaolunHuati> getAllHuati(@Param("begin") int begin,@Param("pageSize") int pageSize);
    //根据文章id在话题点赞表查询点赞量
    public Integer getHuatiLike(Integer topicId);
    //查询数据条数
    public int getCount();

    //管理员删除文章
    public boolean deleteHuati(String topicId);
    //删除文章点赞
    public boolean deleteHuatiLike(String topicId);
    //删除评论
    public boolean deleteComment(Integer commentId);
    //删除评论点赞
    public boolean deleteCommentLike(Integer commentId);
    //删除回复
    public boolean deleteHuifu(Integer responseId);
    //删除回复点赞
    public boolean deleteHuifuLike(Integer responseId);
    //通过文章id查询所有的评论
    public List<AbLuyoutaolunComment> querryCommentBytopicId(String topicId);
    //通过评论id查询所有的回复
    public List<AbLuyoutaolunHuifu> querryHuifuByCommentId(Integer commentId);

    //通过文章id分页查询所有的评论
    public List<AbLuyoutaolunComment> querryCommentBytopicIdAndPage(@Param("begin") int begin,@Param("pageSize") int pageSize,@Param("topicId") String topicId);
    //查询评论条数
    public int getCommentCount(String topicId);
    //根据评论id在评论点赞表查询点赞量
    public Integer getCommentLike(Integer commentId);

    //通过评论id分页查询所有的回复
    public List<AbLuyoutaolunHuifu> querryHuifuBycommentIdAndPage(@Param("begin") int begin,@Param("pageSize") int pageSize,@Param("commentId") String commentId);
    //查询回复条数
    public int getHuifuCount(String commentId);
    //根据回复id在回复点赞表查询点赞量
    public Integer getHuifuLike(Integer responseId);
}
