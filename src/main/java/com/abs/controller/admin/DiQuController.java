package com.abs.controller.admin;

import com.abs.mapper.DiquMapper;
import com.abs.pojo.Diqu;
import com.abs.pojo.remen_diqu;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = "热门地区管理")
@RestController
public class DiQuController {
    @Autowired
    private DiquMapper diquMapper;

    @ApiOperation(value = "分页查询所有热门地区")
    @ResponseBody
    @RequestMapping(value = "/selectDiQuByPage",method = RequestMethod.GET)
    public String selectDiQuByPage(Integer page, Integer limit, String condition){
        int begin = (page - 1) * page;
        if(begin < 0){
            begin = 0;
        }
        JSONObject jsonObject = new JSONObject();
        if(condition == null || condition.equals("")){
            List<remen_diqu> diqus = diquMapper.selectDiQuByPage(begin, limit);
            int count = diquMapper.getCount();
            jsonObject.put("count",count);
            jsonObject.put("data", diqus);
        }else {
            condition = "%"+condition+"%";
            List<remen_diqu> diqus = diquMapper.selectDiQuByCondition(condition);
            int count = diquMapper.getCountByCondition(condition);
            jsonObject.put("count",count);
            jsonObject.put("data", diqus);
        }
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        return jsonObject.toString();
    }

    @ApiOperation(value = "根据关键字（name）查询相关热门地区")
    @ResponseBody
    @RequestMapping(value = "/queryOneDiquByName",method = RequestMethod.GET)
    public String queryOneDiquByName(String name){
        String tit="%"+name+"%";
        List<Diqu> diquList = diquMapper.queryOneDiquByName(tit);
        JSONObject jo = new JSONObject();
        jo.put("diquList",diquList);
        if (diquList!=null){
            System.out.println("模糊查询成功");
            for (Diqu diqu : diquList) {
                System.out.println(diqu);
            }
        }else {
            System.out.println("查询失败");
        }
        return jo.toString();
    }

    @ApiOperation(value = "添加热门地区")
    @ResponseBody
    @RequestMapping(value = "/insertDiQu",method = RequestMethod.POST)
    public String insertDiQu(remen_diqu diqu){
        boolean b = diquMapper.insertDiQu(diqu);
        JSONObject jsonObject = new JSONObject();
        int code = 500;
        if (b){
            code = 200;
        }
        jsonObject.put("code",code);
        return jsonObject.toString();
    }

    @ApiOperation(value = "删除热门地区")
    @ResponseBody
    @RequestMapping(value = "/delDiQu",method = RequestMethod.DELETE)
    public String delDiQu( String[] ids){
        int k = 0;
        int code = 500;
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < ids.length; i++) {
            boolean b = diquMapper.delDiQu(Integer.parseInt(ids[i]));
            if (b){
                k+=1;
            }
        }
        if(k == ids.length){
            code = 200;
        }
        jsonObject.put("code",code);
        return jsonObject.toString();
    }

    @ApiOperation(value = "根据id查询热门地区")
    @ResponseBody
    @RequestMapping(value = "updateDiQu",method = RequestMethod.PUT)
    public String updateDiQu(remen_diqu diqu,HttpSession session){
        Integer id = (Integer) session.getAttribute("updateDiQuId");
        diqu.setId(id);
        boolean b = diquMapper.updateDiQu(diqu);
        JSONObject jsonObject = new JSONObject();
        if(b){
            jsonObject.put("code",200);
        }else {
            jsonObject.put("code",500);
        }
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(value = "selectDiQuById",method = RequestMethod.GET)
    public String selectDiQuById(HttpSession session){
        Integer id = (Integer) session.getAttribute("updateDiQuId");
        JSONObject jsonObject = new JSONObject();
        remen_diqu remen_diqu = diquMapper.selectDiQuById(id);
        jsonObject.put("DiQu",remen_diqu);
        return jsonObject.toString();
    }

}
