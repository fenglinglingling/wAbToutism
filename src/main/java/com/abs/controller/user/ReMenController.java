package com.abs.controller.user;

import com.abs.mapper.ReMenMapper;
import com.abs.mapper.ShouYeMapper;
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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "热门地区")
@RestController
@RequestMapping("/reMen")
public class ReMenController {
    @Autowired
    private ReMenMapper reMenMapper;

    @ApiOperation(value = "获取热门地区列表")
    @ResponseBody
    @RequestMapping(value = "/getAllReMenDiQu", method = RequestMethod.GET)
    public String getAllReMenDiQu() {
        List<remen_diqu> allReMenDiQu = reMenMapper.getAllDiQu();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("allReMenDiQu",allReMenDiQu);
        jsonObject.put("number",allReMenDiQu.size());
        return jsonObject.toString();
    }

    @ApiOperation(value = "获取主页6条热门地区")
    @ResponseBody
    @RequestMapping(value = "/getShouYeReMenDiQu", method = RequestMethod.GET)
    public String getShouYeReMenDiQu(HttpSession session) {
        System.out.println(session.getAttribute("userId"));
        List<remen_diqu> sixReMenDiQu = reMenMapper.getSixDiQu();
        return JSONObject.toJSONString(sixReMenDiQu);
    }

    @ApiOperation(value = "通过地区id获取对应介绍")
    @ResponseBody
    @RequestMapping(value = "/getRmIntroById", method = RequestMethod.GET)
    public String getRmIntroById(int id) {
        String introduction = reMenMapper.getIntroductionById(id);
        return JSONObject.toJSONString(introduction);
    }

}
