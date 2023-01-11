package com.abs.mapper;

import com.abs.pojo.User_message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface User_messageMapper {
    //插入用户的留言
    boolean insertUserQuestion(User_message userMessage);
    //修改用户留言的回答 id:问题的id
    boolean answerUserQuestion(User_message userMessage);
    //通过问题的id删除留言   id:问题的id
    boolean deleteUserQuestion(int id);
    //通过问题的id查找留言   id:问题的id
    User_message selectUserQuestionById(int id);
    //查找status状态为0的留言
    List<User_message> findAllStatusZero(int status);
}
