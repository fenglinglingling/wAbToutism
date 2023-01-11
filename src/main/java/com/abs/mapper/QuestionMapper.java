package com.abs.mapper;

import com.abs.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {
    public boolean insertQuestion(Question question);
    public boolean delQuestion(String title);
    public boolean updateQuestion(Question question);
    public List<Question> queryAllQuestion();
    public List<Question> queryQuestionByTitle(@Param("title") String title);
}
