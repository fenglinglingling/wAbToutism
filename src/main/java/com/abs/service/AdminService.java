package com.abs.service;

import com.abs.pojo.AbZixunQuestions;

import java.util.List;

public interface AdminService {
    List<AbZixunQuestions> getAllQuestion(int begin, int pageSize);
    List<AbZixunQuestions> getKeyQuestion(int begin, int pageSize,String key);
}
