package com.abs.controller.user;

import com.abs.mapper.FoodMapper;
import com.abs.mapper.ShouYeMapper;
import com.abs.pojo.meishi_food;
import com.abs.pojo.remen_diqu;
import com.abs.pojo.shouye_diqu;
import com.abs.pojo.shouye_zixun;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "地区美食")
@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodMapper foodMapper;

    @ApiOperation(value = "获取美食列表")
    @ResponseBody
    @RequestMapping(value = "/getAllFood", method = RequestMethod.GET)
    public String getAllFood() {
        List<meishi_food> allFood = foodMapper.getAllFood();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("allFood",allFood);
        jsonObject.put("number",allFood.size());
        return jsonObject.toString();
    }
    @ApiOperation(value = "获取主页4条美食")
    @ResponseBody
    @RequestMapping(value = "/getShouYeMeiShi", method = RequestMethod.GET)
    public String getShouYeMeiShi() {
        List<meishi_food> fourMeiShi = foodMapper.getFourFood();
        return JSONObject.toJSONString(fourMeiShi);
    }

    @ApiOperation(value = "通过美食id进行赞(type = 1)或踩(type = -1)")
    @ResponseBody
    @RequestMapping(value = "/foodZanOrCai", method = RequestMethod.GET)
    public String foodZanOrCai(int id, int type) {
        boolean flag = foodMapper.zanOrCaiById(id, type);
        if (flag)
            return JSONObject.toJSONString(id);
        else
            return JSONObject.toJSONString(id);
    }

}
