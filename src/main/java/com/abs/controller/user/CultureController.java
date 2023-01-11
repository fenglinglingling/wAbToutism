package com.abs.controller.user;

import com.abs.mapper.*;
import com.abs.pojo.*;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "特色文化")
@RestController
@RequestMapping("/culture")
public class CultureController {
    @Autowired
    private CultureMapper cultureMapper;

    @Autowired
    private AbCultureActivityMapper abCultureActivityMapper;
    @Autowired
    private AbCultureHeritageMapper abCultureHeritageMapper;
    @Autowired
    private AbCultureVedioMapper abCultureVedioMapper;
    @Autowired
    private AbCultureFestivalMapper abCultureFestivalMapper;


    @ApiOperation(value = "通过name获取文化活动对象")
    @ResponseBody
    @RequestMapping(value = "/getCultureActivityInfoByName", method = RequestMethod.GET)
    public String getCultureActivityInfoByName() {
        AbCultureActivity abCultureInfo = abCultureActivityMapper.AbActivityInfoByName(AbCultureActivity.ActivityName);
        JSONObject jb = new JSONObject();
        jb.put("data", abCultureInfo);
        return jb.toString();
    }

    @ApiOperation(value = "获取非遗文化列表")
    @ResponseBody
    @RequestMapping(value = "/getCultureHeritageList", method = RequestMethod.GET)
    public String getCultureHeritageList() {
        List<AbCultureHeritage> allCulHeritageList = abCultureHeritageMapper.getAllCulHeritageList();
        JSONObject jb = new JSONObject();
        jb.put("data", allCulHeritageList);
        return jb.toString();
    }

    @ApiOperation(value = "通过id获取非遗文化对象")
    @ResponseBody
    @RequestMapping(value = "/getHerigateCultureById", method = RequestMethod.GET)
    public String getHerigateCultureById() {
        AbCultureHeritage abCultureHeritage = abCultureHeritageMapper.AbHerigateInfoById(AbCultureHeritage.HerigateId);
        JSONObject jb = new JSONObject();
        jb.put("data", abCultureHeritage);
        return jb.toString();
    }


    @ApiOperation(value = "获取视频列表")
    @ResponseBody
    @RequestMapping(value = "/getCultureHeritageVedioList", method = RequestMethod.GET)
    public String getCultureHeritageVedioList() {
        List<AbCultureVedio> vedioList = abCultureVedioMapper.getAllAbCulVedioList();
        JSONObject jb = new JSONObject();
        jb.put("data", vedioList);
        return jb.toString();
    }

    @ApiOperation(value = "通过name获取文化节日对象")
    @ResponseBody
    @RequestMapping(value = "/getFestivalInfoByName", method = RequestMethod.GET)
    public String getFestivalInfoByName() {
        AbCultureFestival festivalInfoByName = this.abCultureFestivalMapper.AbFestivalInfoByName(AbCultureFestival.FestivalName);
        JSONObject jb = new JSONObject();
        jb.put("data", festivalInfoByName);
        return jb.toString();
    }

    @ApiOperation(value = "获取文化列表")
    @ResponseBody
    @RequestMapping(value = "/getAllCulture", method = RequestMethod.GET)
    public String getAllCulture() {
        List<culture_wenhua> allCulture = cultureMapper.getAllCulture();
        return JSONObject.toJSONString(allCulture);
    }

    @ApiOperation(value = "获取首页4文化列表")
    @ResponseBody
    @RequestMapping(value = "/getFourCulture", method = RequestMethod.GET)
    public String getFourCulture() {
        List<culture_wenhua> fourCulture = cultureMapper.getFourCulture();
        return JSONObject.toJSONString(fourCulture);
    }

}
