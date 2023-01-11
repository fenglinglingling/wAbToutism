package com.abs.controller.user;

import com.abs.mapper.UserInfoMapper;
import com.abs.pojo.AbUser;
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

@RestController
@Api(tags = "个人信息管理")
public class UserLoginController {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @ApiOperation("查询用户个人信息")
    @ResponseBody
    @RequestMapping(value = "getLoginUserInfo",method = RequestMethod.GET)
    public String getAllUser(HttpSession session){
        System.out.println(session.getAttribute("userId"));
        AbUser user = userInfoMapper.findUpdateUser(session.getAttribute("userId").toString());
        JSONObject object=new JSONObject();
        object.put("data",user);
        return object.toString();
    }


    @ApiOperation(value = "修改User个人信息")
    @RequestMapping(value = "/editUserGeRenInfo",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String editUserGeRenInfo(AbUser abUser,HttpSession session) {
        System.out.println(abUser);
        abUser.setUserId(session.getAttribute("userId").toString());
        JSONObject object=new JSONObject();
        if(userInfoMapper.editUserGeRenInfo(abUser)){
            object.put("code",200);
            object.put("msg","修改成功");
            object.put("data","");
        }else {
            object.put("code",500);
            object.put("msg","修改失败");
            object.put("data","");
        }

        return object.toString();
    }
}
