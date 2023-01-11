package com.abs.service.impl;

import com.abs.mapper.ZiXunMapper;
import com.abs.pojo.AbZixunQuestions;
import com.abs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ZiXunMapper ziXunMapper;


    @Override
    public List<AbZixunQuestions> getAllQuestion(int page, int pageSize) {
        int begin = (page - 1) * pageSize;
        if (begin < 0)
            begin = 0;
        return ziXunMapper.getAllQuestion(begin, pageSize);
    }

    @Override
    public List<AbZixunQuestions> getKeyQuestion(int page, int pageSize, String key) {
        int begin = (page - 1) * pageSize;
        if (begin < 0)
            begin = 0;
        return ziXunMapper.getKeyQuestion(begin, pageSize,key);
    }
}
