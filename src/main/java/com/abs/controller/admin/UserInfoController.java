package com.abs.controller.admin;

import com.abs.pojo.AbUser;
import com.abs.mapper.UserInfoMapper;
import com.abs.pojo.User_message;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@RestController
@Api(tags = "个人信息管理")
public class UserInfoController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @ApiOperation("查询所有的用户信息")
    @ResponseBody
    @RequestMapping(value = "getAllUser",method = RequestMethod.GET)
    public String getAllUser(){
        List<AbUser> allUser = userInfoMapper.getAllUser();
        JSONObject object=new JSONObject();
        object.put("allUser",allUser);
        return object.toString();
    }

    @ApiOperation("通过用户名模糊查询用户信息")
    @ResponseBody
    @RequestMapping(value = "getAllUserByUserName",method = RequestMethod.GET)
    public String getAllUserByUserName(String atr){
        List<AbUser> allUser = userInfoMapper.getAllUserByUserName(atr);
        JSONObject object=new JSONObject();
        object.put("allUser",allUser);
        return object.toString();
    }

    @ApiOperation("通过用户id删除用户信息")
    @ResponseBody
    @RequestMapping(value = "deleteUser",method = RequestMethod.DELETE)
    public String deleteUser(String atr){
        JSONObject object=new JSONObject();
        if(userInfoMapper.deleteUser(atr)){
            object.put("msg","删除成功");
        }else {
            object.put("msg","删除失败");
        }


        return object.toString();
    }

//-----------------------------------------------------------------------------------------------
@ApiOperation(value = "通过id得到User信息")
@RequestMapping(value = "/exchangeImgFile",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
@ResponseBody
public String exchangeImgFile(@RequestParam MultipartFile uploadFile, HttpSession session) throws Exception {
    JSONObject jb = new JSONObject();
    String realPath = "D:\\java\\IdeaProjects\\wAbTourism\\src\\main\\resources\\static\\image";
    System.out.println(realPath);
    //判断上传文件是否存在
    if (uploadFile==null){
        System.out.println("没有得到客户端的文件");
        jb.put( "code",500);
        jb.put( "msg", "没有得到客户端的文件");
        jb.put( "data","");
        return jb.toString();
    }else{
        String filename = uploadFile.getOriginalFilename();
        System.out.println(filename);
        //判断文件名是否存在
        if (filename.equals("")){
            jb.put( "code",500);
            jb.put( "msg", "");
            jb.put( "data","");
            //没有文件名
            return jb.toString();
        }else{
//                File file=new File(root,filename);//保存在服务器的文件对象
            uploadFile.transferTo(new File(realPath,filename));
            userInfoMapper.editUserImg("1",filename);
            jb.put( "code",200);
            jb.put( "msg", "");
            jb.put( "data",filename);
        }
    }
    return jb.toString();

}


@ApiOperation(value = "通过id得到User信息")
@RequestMapping(value = "/getUserInfo",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
@ResponseBody
public String getUserInfo() {
    JSONObject object=new JSONObject();
    AbUser user = userInfoMapper.findUpdateUser(AbUser.USERID);
    if(user==null || user.equals("")){
        object.put("code",500);
        object.put("msg","添加失败");
        object.put("data","");
    }else {
        object.put("code",200);
        object.put("msg","添加成功");
        object.put("data",user);
    }
    return object.toString();
}


    @ApiOperation(value = "添加User")
    @RequestMapping(value = "/userAdd",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String userAdd(AbUser abUser) {
        System.out.println(abUser);
        JSONObject object=new JSONObject();
        if(userInfoMapper.addUser(abUser)){
            object.put("code",200);
            object.put("msg","添加成功");
            object.put("data","");
        }else {
            object.put("code",500);
            object.put("msg","添加失败");
            object.put("data","");
        }

        return object.toString();
    }
    @ApiOperation(value = "通过id找到要修改的User")
    @RequestMapping(value = "/findUpdateUser",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public String findUpdateUser() {
        JSONObject object=new JSONObject();
        AbUser user = userInfoMapper.findUpdateUser(AbUser.USERID);
        if(user==null || user.equals("")){
            object.put("code",500);
            object.put("msg","查找失败");
            object.put("data","");
        }else {
            object.put("code",200);
            object.put("msg","查找成功");
            object.put("data",user);
        }
        return object.toString();
    }

    @ApiOperation(value = "修改User")
    @RequestMapping(value = "/updateUser",produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(AbUser abUser) {
        System.out.println(abUser);
        JSONObject object=new JSONObject();
        if(userInfoMapper.editUser(abUser)){
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
    //删除学生
    @ApiOperation(value = "删除User")
    @RequestMapping(value = "/delUser",produces = "application/json;charset=UTF-8", method = RequestMethod.DELETE)
    @ResponseBody
    public String delUser(String userId){
        System.out.println(userId);
        JSONObject jsonObject = new JSONObject();
        try {
            String[] ids = userId.split(",");
            System.out.println(ids);
            int count=0;
            for (int i = 0; i < ids.length; i++) {
                if (userInfoMapper.deleteUser(ids[i])){
                    count++;
                    jsonObject.put("code",200);
                }else {
                    jsonObject.put("code",500);
                }
            }
            jsonObject.put("count",count);
        } catch (Exception e) {
            jsonObject.put("code",500);
        }
        return jsonObject.toString();
    }

    @ApiOperation(value = "加载user数据表格")
    @RequestMapping(value = "/getUserDataTable", method = RequestMethod.GET)
    @ResponseBody
    public String getUserDataTable(int page,int limit,String search_key){
        System.out.println("===================>"+search_key);
        JSONObject jb =new JSONObject();
        if (search_key==null || search_key.equals(" ")){
            page=(page-1)*limit;
            if (page<0)page=0;
            List<AbUser> allUser = userInfoMapper.getUserByPage(page, limit);
            int count = allUser.size();
            System.out.println("测试用户数量："+count);
            jb.put( "code", 0);
            jb.put( "msg", "");
            jb.put( "count",count);
            jb.put( "data",allUser);
            return jb.toString();
        }else{
            page=(page-1)*limit;
            if (page<0)page=0;
            search_key="%"+search_key+"%";
            List<AbUser> allUser = userInfoMapper.getUserByPageByKey(page, limit,search_key);
            int count = allUser.size();
            System.out.println("测试用户数量："+count);
            jb.put( "code", 0);
            jb.put( "msg", "");
            jb.put( "count",count);
            jb.put( "data",allUser);
            return jb.toString();
        }
    }





}
