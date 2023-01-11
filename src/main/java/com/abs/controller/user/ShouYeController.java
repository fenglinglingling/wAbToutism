package com.abs.controller.user;

import com.abs.mapper.ShouYeMapper;
import com.abs.pojo.shouye_diqu;
import com.abs.pojo.shouye_zixun;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "首页")
@RestController
@RequestMapping("/shouYe")
public class ShouYeController {
    @Autowired
    private ShouYeMapper shouYeMapper;

    @ApiOperation(value = "获取资讯名称列表")
    @ResponseBody
    @RequestMapping(value = "/getAllZiXunName", method = RequestMethod.GET)
    public String getAllZiXunName() {
        List<shouye_zixun> allZiXun = shouYeMapper.getAllZiXun();
        List<String> nameList = new ArrayList<>();
        for (shouye_zixun zixun : allZiXun) {
            nameList.add(zixun.getTitle());
        }
        return JSONObject.toJSONString(nameList);
    }

    @ApiOperation(value = "通过资讯id获取对应内容")
    @ResponseBody
    @RequestMapping(value = "/getZiXunContentById", method = RequestMethod.GET)
    public String getContentById(int id) {
        String introduction = shouYeMapper.getContentById(id);
        return JSONObject.toJSONString(introduction);
    }

    @ApiOperation(value = "获取地区列表")
    @ResponseBody
    @RequestMapping(value = "/getAllDiQu", method = RequestMethod.GET)
    public String getAllDiQu() {
        List<shouye_diqu> allDiQu = shouYeMapper.getAllDiQu();
        return JSONObject.toJSONString(allDiQu);
    }

}
