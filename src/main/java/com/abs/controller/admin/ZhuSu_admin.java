package com.abs.controller.admin;

import com.abs.mapper.ZhusuMapper;
import com.abs.pojo.AbZhusuZhusu;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


////////后台---住宿管理
//采用rest风格
@ApiOperation("后台---住宿管理")
@RestController
@RequestMapping(value = "/ZhuSu_admin", produces = "application/json;charset=utf-8")
public class ZhuSu_admin {
    @Autowired
    private ZhusuMapper zhusuMapper;
    @ApiOperation(" 后台-》获取住宿分页列表")
    @GetMapping()
    public String pageListzhusu(Integer page, Integer limit, String search){
        JSONObject jsonObject = new JSONObject();

        int begin = (page - 1) * page;
        if(begin < 0){
            begin = 0;
        }
        List<AbZhusuZhusu> abZhusuZhusus = zhusuMapper.selectZhuSuByPage(begin, limit, search);
        jsonObject.put("data", abZhusuZhusus);
        jsonObject.put("count", zhusuMapper.selectAllZhuSus().size());
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        return jsonObject.toString();
    }
    @GetMapping("/{id}")
    public String getbyid(@PathVariable String id){
        JSONObject jsonObject = new JSONObject();
        System.out.println("2ddddddddd"+id);
        AbZhusuZhusu zhusuId = zhusuMapper.getZhusuId(Integer.valueOf(id));
        if (zhusuId!=null){
            jsonObject.put("data", zhusuId);
            jsonObject.put("code", 200);
            jsonObject.put("msg", "OK");
        }else {
            jsonObject.put("code", 300);
            jsonObject.put("msg", "NO");
        }

        return jsonObject.toJSONString();
    }
    @DeleteMapping
    public String deletezhusu(String ids){
        JSONObject jsonObject = new JSONObject();
        String[] id = ids.split(",");
        for (String s : id) {
            try {
                if (zhusuMapper.delete(Integer.valueOf(s))) {

                }
            } catch (Exception e) {
            }

        }
        jsonObject.put("code", 200);
        jsonObject.put("msg", "delete_ok");
         return jsonObject.toJSONString();
    }
    @PostMapping
    private String savazhusu(AbZhusuZhusu abZhusuZhusu){
//        /static/uploadFile/zhusu/image/2.png
        JSONObject jsonObject = new JSONObject();
        if (zhusuMapper.sava(abZhusuZhusu)) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "sava_ok");
        }else {
            jsonObject.put("code", 300);
            jsonObject.put("msg", "dsava_no");
        }

        return jsonObject.toJSONString();
    }

    @PutMapping
    private String updatezhusu(AbZhusuZhusu abZhusuZhusu){
        JSONObject jsonObject = new JSONObject();
        if (zhusuMapper.update(abZhusuZhusu)) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "update_ok");
        }else {
            jsonObject.put("code", 300);
            jsonObject.put("msg", "update_no");
        }
        return jsonObject.toJSONString();
    }

}
