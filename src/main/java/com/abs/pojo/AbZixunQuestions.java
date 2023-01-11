package com.abs.pojo;

import java.io.Serializable;

/**
 * (AbZixunQuestions)实体类
 *
 * @author makejava
 * @since 2022-11-08 19:12:26
 */
public class AbZixunQuestions implements Serializable {
    private static final long serialVersionUID = -48720237361119529L;
    
    private Integer questionId;
    
    private String name;
    
    private String content;
    
    private Integer type;


    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}

