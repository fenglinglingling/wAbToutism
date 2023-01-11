package com.abs.controller.user;

import com.abs.mapper.JingquMapper;
import com.abs.pojo.AbLuyouyindaoJingqu;
import com.abs.pojo.AbZhusuOrders;
import com.abs.pojo.AbZhusuZhusu;
import com.abs.mapper.ZhusuMapper;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "旅客住宿")
@RequestMapping("/zhusu")
public class ZhuSuController {
    @Autowired
    private ZhusuMapper zhusuMapper;

    @Autowired
    private JingquMapper jingquMapper;

    @ApiOperation("获取景区对应的住宿列表")
    @ResponseBody
    @RequestMapping(value = { "/selectZhuShuListAndJqAllID"},method = RequestMethod.GET,produces="application/json;charset=utf-8" )
    public JSONObject seleceZhuShuListByJqAllID() throws JSONException {
        List<AbLuyouyindaoJingqu> jingquList = jingquMapper.seleceZhuShuListByJqAllID();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jingquList);
//        for (int i = 0; i < jingquList.size(); i++) {
//            jsonObject.put("jingquList"+i, jingquList);
//        }
        return jsonObject;
    }


    @ResponseBody
    @ApiOperation("获取住宿列表")
    @RequestMapping(value = { "/selectAllZhuSus"},method = RequestMethod.GET)
    public String selectAllZhuSus() throws JSONException {
        List<AbZhusuZhusu> abZhusuZhusus = zhusuMapper.selectAllZhuSus();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shusus", abZhusuZhusus);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("获取住宿列表按热度进行降序排列")
    @RequestMapping(value = { "/selectByHot"},method = RequestMethod.GET)
    public String selectByHot() throws JSONException {
        List<AbZhusuZhusu> abZhusuZhusus = zhusuMapper.selectByHot();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zhusus",abZhusuZhusus);
        return jsonObject.toString();
    }
    @ResponseBody
    @ApiOperation("按热度获取首页4条住宿列表")
    @RequestMapping(value = { "/ShouYeZhuSuByHot"},method = RequestMethod.GET)
    public String ShouYeZhuSuByHot() throws JSONException {
        List<AbZhusuZhusu> abZhusuZhusus = zhusuMapper.selectFourByHot();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zhusus",abZhusuZhusus);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过住宿类型进行查询")
    @RequestMapping(value = { "/selectByType"},method = RequestMethod.GET)
    public String selectByType(String type) throws JSONException {
        List<AbZhusuZhusu> abZhusuZhusus = zhusuMapper.selectByType(type);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zhusus",abZhusuZhusus);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过住宿名进行查询")
    @RequestMapping(value = { "/selectByName"},method = RequestMethod.GET)
    public String selectByName(String name) throws JSONException {
        AbZhusuZhusu abZhusuZhusu = zhusuMapper.selectByName(name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zhusus",abZhusuZhusu);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过住宿id进行查询")
    @RequestMapping(value = { "/selectById"},method = RequestMethod.GET)
    public String selectById(Integer id) throws JSONException {
        AbZhusuZhusu abZhusuZhusu = zhusuMapper.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zhusus",abZhusuZhusu);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过住宿id查询最近购买记录")
    @RequestMapping(value = { "/selectOrders"},method = RequestMethod.GET)
    public String selectOrders(Integer id) throws JSONException {
        List<AbZhusuOrders> abZhusuOrders = zhusuMapper.selectOrders(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zhusus",abZhusuOrders);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("手动添加购房记录")
    @RequestMapping(value = { "/addOrders"},method = RequestMethod.GET)
    public String addOrders(AbZhusuOrders orders) throws JSONException {
        boolean b = zhusuMapper.addOrders(orders);
        String re = "手动添加购房记录失败";
        if (b){
            re = "手动添加购房记录成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addOrders_result",re);
        return jsonObject.toString();
    }

}
