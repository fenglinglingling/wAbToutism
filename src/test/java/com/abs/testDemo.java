package com.abs;

import com.abs.mapper.ShouYeMapper;
import com.abs.pojo.shouye_zixun;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给测试类加上@WebAppConfiguration。
public class testDemo {
    @Autowired
    private ShouYeMapper shouYeMapper;

    @Test
    public void readExcel() {
        JSONObject jsonObject = new JSONObject();
        List<shouye_zixun> allZiXun = shouYeMapper.getAllZiXun();
        List<String> nameList = new ArrayList<>();
        for (shouye_zixun zixun : allZiXun) {
            nameList.add(zixun.getTitle());
        }
        jsonObject.put("nameList", nameList);
        System.out.println(jsonObject);
    }

}
