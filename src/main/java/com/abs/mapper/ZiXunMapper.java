package com.abs.mapper;


import com.abs.pojo.AbZixunQuestions;
import com.abs.pojo.AbZixunReply;
import com.abs.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
//仅供参考，可以删除！！！！！！！！！！！！！！！！！！！！！！！！

@Repository
@Mapper
public interface ZiXunMapper {
    //获取全部问题数量
    public int getCount();

    //获取key问题数量
    public int getCountByKey(@Param("key") String key);

    //查询所有的问题
    public List<AbZixunQuestions> getAllQuestion(@Param("begin") int begin, @Param("pageSize") int pageSize);

    public List<AbZixunQuestions> getKeyQuestion(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("key") String key);

    //根据类型查询问题
    public List<AbZixunQuestions> getQuestionByType(Integer atr);

    //根据类型查询问题
    public AbZixunQuestions getQuestionById(Integer atr);


    //查询所有回复
    public List<AbZixunReply> getAllReply();

    //通过问题id查询回复
    public AbZixunReply getReplyByQuestion(Integer atr);

    //删除问题
    public boolean deleteQuestion(Integer atr);

    public boolean deleteReplyByQuestion(Integer atr);

    //删除回复
    public boolean deleteReply(Integer atr);

    public boolean deleteQRWithJQR(@Param("q") String qContent, @Param("r") String rContent);

    //添加回复
    public boolean savaReply(AbZixunReply reply);

    //修改问题类型
    public boolean updateQuestionType(int questionId);

    //添加问题与回复到机器人中
    public boolean addQRtoJQR(@Param("id") String id, @Param("question") String question, @Param("content") String content);

    //添加问题
    public boolean savaQuestion(AbZixunQuestions questions);

}
