package com.abs.controller.user;

import com.abs.Tool.*;
import com.abs.mapper.UserMapper;
import com.abs.pojo.*;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Api(tags = "模块操作")
@RestController
@RequestMapping(value = "User", produces = "application/json;charset=utf-8")
public class UserController {
    // session是每个客户端唯一的 建议 用户信息 通过session（比cooks更能存）存到客户端
    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "user_Register", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户电话/email", name = "phoneOremail", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "用户密码", name = "password", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(value = "验证码", name = "VerifyCodeUtil", required = true, paramType = "query", dataType = "String"),})
    public String user_RegisterAndLogin(@RequestParam String phoneOremail, @RequestParam String password, @RequestParam String VerifyCode, HttpServletRequest request, MultipartFile file, RedirectAttributes attr) throws Exception {
        JSONObject jsonObject = new JSONObject();
        System.out.println("验证码 传入++++==" + VerifyCode);
        //判断 传入的数据是否存在
        if (phoneOremail != null && password != null && VerifyCode != null) {
            //先判断验证码是否正确
            //从session中取出验证码  session是每个客户端唯一的
            String Code = (String) request.getSession().getAttribute("VerifyCode");
            System.out.println("验证码取出】】【【==" + Code);
            if (VerifyCode.equals(Code)) {//验证码正确

                //先判断该注册id是否存在  修改：  分开查询 phone email



                if (phoneOremail.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")){//注册的是邮箱
                    User userByEmail = userMapper.getUserByEmail(phoneOremail);
                    if (userByEmail == null) {//不存在
                        //注册

                        //系统生成user_id
                        //判断生成的id 是否存在
                        //存在 重新生成  不存在  进行注册操作

                        UuidUtil uuidUtil = new UuidUtil();
                        String uuid = uuidUtil.randomNumberIsTen().toString();
                        while (userMapper.getUserById(uuid) != null) {//uuid存在就继续生成uuid
                            uuid = uuidUtil.randomNumberIsTen().toString();
                        }
                        //加密 密码password
                        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                        String passwordToken = bCryptPasswordEncoder.encode(password);//明文加密
//                        System.out.println(bCryptPasswordEncoder.matches(a, encode));

                        User userEmail = new User();
                        userEmail.setUser_id(uuid);//user_id  系统生成
                        userEmail.setUser_email(phoneOremail);
                        userEmail.setUser_name("普通用户" + phoneOremail);
                        userEmail.setUser_role("user");//用户开始注册的角色：user
                        userEmail.setUser_password(passwordToken);////////可以换为加密
                        userEmail.setUser_gender("男");///用户性别  默认为：男
                        userEmail.setUser_instruction("测试 密码为：" + password);/////测试阶段 密码显示；
                        //图片路径
//                    User_ImageUtil user_imageUtil = new User_ImageUtil();
//                    String savaimagepath = user_imageUtil.savaimage(request, file, phoneOremail);
//                    System.out.println("图片保存路径：：" + savaimagepath);
//                    users.setUser_image(savaimagepath);////用户的头像

                        if (userMapper.register(userEmail)) {//注册成功
                            //注册成功转发到其他页面
                            jsonObject.put("code", "200");
                            jsonObject.put("message", "注册成功");
                            request.getSession().removeAttribute("VerifyCodeUtil");//作废这次操作的session
                            return jsonObject.toJSONString();
                        } else {//注册失败
                            jsonObject.put("code", "300");
                            jsonObject.put("message", "注册失败（请重试/或者尝试更换邮箱/手机号注册");
                            request.getSession().removeAttribute("VerifyCodeUtil");//作废这次操作的验证码
                            return jsonObject.toJSONString();
                        }
                    } else {//用户存在
                        jsonObject.put("code", "300");
                        jsonObject.put("message", "注册失败 该邮箱已被注册，请更换邮箱注册");
                        request.getSession().removeAttribute("VerifyCodeUtil");//作废这次操作的验证码
                        return jsonObject.toJSONString();
                    }


                }else if (phoneOremail.matches("^1[3-9]\\d{9}$")){//注册的是电话
                    User userByPhone = userMapper.getUserByPhone(phoneOremail); //查询 该注册的手机号是否有该用户
                    if (userByPhone == null) {//不存在
                        //注册

                        //系统生成user_id
                        //判断生成的id 是否存在
                        //存在 重新生成  不存在  进行注册操作

                        UuidUtil uuidUtil = new UuidUtil();
                        String uuid = uuidUtil.randomNumberIsTen().toString();
                        while (userMapper.getUserById(uuid) != null) {//uuid存在就继续生成uuid
                            uuid = uuidUtil.randomNumberIsTen().toString();
                        }
                        //加密 密码password
                        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                        String passwordToken = bCryptPasswordEncoder.encode(password);//明文加密
//                        System.out.println(bCryptPasswordEncoder.matches(a, encode));

                        User userPhone = new User();
                        userPhone.setUser_id(uuid);//user_id  系统生成
                        userPhone.setUser_phone(phoneOremail);
                        userPhone.setUser_name("普通用户" + phoneOremail);
                        userPhone.setUser_role("user");//用户开始注册的角色：user
                        userPhone.setUser_password(passwordToken);////////可以换为加密
                        userPhone.setUser_gender("男");///用户性别  默认为：男
                        userPhone.setUser_instruction("测试 密码为：" + password);/////测试阶段 密码显示；
                        //图片路径
//                    User_ImageUtil user_imageUtil = new User_ImageUtil();
//                    String savaimagepath = user_imageUtil.savaimage(request, file, phoneOremail);
//                    System.out.println("图片保存路径：：" + savaimagepath);
//                    users.setUser_image(savaimagepath);////用户的头像

                        if (userMapper.register(userPhone)) {//注册成功
                            //注册成功转发到其他页面
                            jsonObject.put("code", "200");
                            jsonObject.put("message", "注册成功");
                            request.getSession().removeAttribute("VerifyCodeUtil");//作废这次操作的session
                            return jsonObject.toJSONString();
                        } else {//注册失败
                            jsonObject.put("code", "300");
                            jsonObject.put("message", "注册失败（请重试/或者尝试更换手机号注册");
                            request.getSession().removeAttribute("VerifyCodeUtil");//作废这次操作的验证码
                            return jsonObject.toJSONString();
                        }
                    } else {//用户存在
                        jsonObject.put("code", "300");
                        jsonObject.put("message", "注册失败 该手机号已被注册，请更换手机号/邮箱注册");
                        request.getSession().removeAttribute("VerifyCodeUtil");//作废这次操作的验证码
                        return jsonObject.toJSONString();
                    }
                }else {//输入错误
                    jsonObject.put("code", "300");
                    jsonObject.put("message", "注册失败 手机号/邮箱格式错误，请输入正确的手机号/邮箱");
                    return jsonObject.toJSONString();
                }



            } else {//验证码错误
                jsonObject.put("code", "300");
                jsonObject.put("message", "验证码错误");
                request.getSession().removeAttribute("VerifyCodeUtil");//作废这次操作的验证码
                return jsonObject.toJSONString();
            }

        } else {//前端传入的数据不完整
            jsonObject.put("code", "300");
            jsonObject.put("message", "前端传入的数据不完整");
            request.getSession().removeAttribute("VerifyCodeUtil");//作废这次操作的验证码
            return jsonObject.toJSONString();
        }
    }


    @ApiOperation(value = "验证码 登录页面获取该验证码图片，并且会把验证码放入session中，登录或者注册的时候 也要携带该session 传到后端验证")
    @RequestMapping(value = "get_VerifyCode", method = RequestMethod.GET)
    public void identifyImage(HttpServletResponse response, HttpSession session) {
        //创建随机验证码
        VerifyCodeUtil codeutil = new VerifyCodeUtil();
        String code = codeutil.getIdentifyCode();//验证码
        //session存入验证码
        session.setAttribute("VerifyCode", code);
        //根据验证码创建图片
        BufferedImage codeImage = codeutil.getIdentifyImage(code);//验证码转换为img
        codeutil.responseIdentifyImg(codeImage, response);
    }


    @ApiOperation(value = "找回密码 验证环节")
    @RequestMapping(value = "findPassword", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findPassword(String toCode,String who,HttpSession session){
        JSONObject jsonObject = new JSONObject();

        if (who!=null&& who.equals("1")){//发送验证码  需要判断是手机还是邮箱
            if (toCode!=null){
                if (toCode.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {//是邮箱
                    System.out.println("email+"+toCode);
                    EmailCodeTool emailCodeTool = new EmailCodeTool();
                    String s = emailCodeTool.DoAjax(toCode);
                    System.out.println("Controller"+s);
                    if (s!=null){
                        session.setAttribute("VerifyCode_findpassword",s);
                        jsonObject.put("code","200");
                        jsonObject.put("message","验证获取成功");

                    }else {
                        jsonObject.put("code","300");
                        jsonObject.put("message","验证获取失败");
                    }

                }else if (toCode.matches("^1[3-9]\\d{9}$")){
                    jsonObject.put("code","300");
                    jsonObject.put("message","暂未开通电话号码找回功能");

                }else {
                    jsonObject.put("code","300");
                    jsonObject.put("message","电话号码或者邮箱格式错误 请正确输入");
                }
            }
        } else if (who!=null&& who.equals("2")) {//验证码比较 是否正确。
            String s = (String) session.getAttribute("VerifyCode_findpassword");

            if (toCode!=null){
                if (s!=null){
                    if (s.equals(toCode)){
                        jsonObject.put("code","200");
                        jsonObject.put("message","验证OK");
                    }else {
                        jsonObject.put("code","300");
                        jsonObject.put("message","验证错误");
                    }
                }
            }

        }

        return jsonObject.toJSONString();

    }

    @ApiOperation(value = "找回密码 设置新密码环节环节")
    @RequestMapping(value = "findPassword_updataPassword", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findPassword_updataPassword(String password,String email_photo ){
        JSONObject jsonObject = new JSONObject();
        if (password!=null&&email_photo!=null){
            User user = new User();
            if (email_photo.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")){//邮箱
                user=userMapper.getUserByEmail(email_photo);
            }else if (email_photo.matches("^1[3-9]\\d{9}$")) {
                user = userMapper.getUserByPhone(email_photo);
            }

            if (user!=null){// 存在 进行修改
                User user1 = new User();
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String passwordToken = bCryptPasswordEncoder.encode(password);//明文加密
                user1.setUser_password(passwordToken);
                user1.setUser_id(user.getUser_id());
                if (userMapper.UpdateUser(user1)) {
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "找回成功");
                } else {
                    jsonObject.put("code", "300");
                    jsonObject.put("message", "找回成功");
                }
            }else {//不做处理

            }


            }else {
            jsonObject.put("message","数据错误");
            jsonObject.put("code","300");
        }
        return jsonObject.toJSONString();

    }


/////////////////、、、、、、、、、、、、、、、、//////以上都是用到了











    @ApiOperation(value = "更改用户信息   其中必须要有user_id 以及至少要修改一个参数")
    @RequestMapping(value = "user_update", method = RequestMethod.PUT)
    @ResponseBody
    public String user_update(@ApiParam(value = "用户更改后信息", required = true) @RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        if (user.getUser_id() != null && !user.getUser_id().equals("")) {
            if (user.getUser_password() != null) {//若是修改密码  则加密密码
                //加密 密码password
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String passwordToken = bCryptPasswordEncoder.encode(user.getUser_password());//明文加密
                user.setUser_password(passwordToken);
            }

            if (userMapper.UpdateUser(user)) {
                jsonObject.put("code", "200");
                jsonObject.put("message", "更改成功");
            } else {
                jsonObject.put("code", "300");
                jsonObject.put("message", "更改失败");
            }
        }
        return jsonObject.toJSONString();
    }


    @ApiOperation(value = "删除用户(用户注销)")
    @RequestMapping(value = "user_delete", method = RequestMethod.DELETE)
    @ApiImplicitParam(value = "用户id", name = "id", required = true, paramType = "query", dataType = "String")
    @ResponseBody
    public String user_delete(@RequestParam String id) {
        JSONObject jsonObject = new JSONObject();
        if (!id.equals("")) {
            if (userMapper.DeleteUserById(id)) {
                jsonObject.put("code", "200");
                jsonObject.put("message", "账户删除成功，期待下次使用~~~~");
            } else {
                jsonObject.put("code", "300");
                jsonObject.put("message", "删除用户失败");
            }
        }
        return jsonObject.toJSONString();
    }


    @ApiOperation(value = "获取用户住宿记录列表")
    @RequestMapping(value = "user_zhusu", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParam(value = "用户id", name = "id", required = true, paramType = "query", dataType = "String")
    public String user_zhusu(String id) {
        JSONObject jsonObject = new JSONObject();

        User_PageFenyeUtil user_pageFenyeUtil = new User_PageFenyeUtil();
        Map<String, Integer> fenye = user_pageFenyeUtil.Fenye(0, 0);//分页工具  pageSize 为o查询全部

        List<User_zhusu> zhusuList = userMapper.getUserStaysById(id, fenye);

        if (zhusuList.size() > 0) {
            jsonObject.put("code", "200");
            jsonObject.put("message", "查询成功");
            jsonObject.put("data", zhusuList);
        } else {
            jsonObject.put("code", "300");
            jsonObject.put("message", "没有查询到住宿记录");
        }
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "获取用户行程")
    @RequestMapping(value = "user_getLineList", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParam(value = "用户id", name = "id", required = true, paramType = "query", dataType = "String")
    public String user_getLineList(@RequestParam String id) {
        JSONObject jsonObject = new JSONObject();
        User_PageFenyeUtil user_pageFenyeUtil = new User_PageFenyeUtil();
        Map<String, Integer> fenye = user_pageFenyeUtil.Fenye(0, 0);//分页工具  pageSize 为o查询全部

        List<AbLuyouyindaoLuxian> userLineList = userMapper.getUserLineById(id, fenye);
        if (userLineList.size() > 0) {
            jsonObject.put("code", "200");
            jsonObject.put("message", "查询用户行程成功");
            jsonObject.put("data", userLineList);
        } else {
            jsonObject.put("code", "300");
            jsonObject.put("message", "没有查询到用户行程");
        }
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "获取用户发布的话题")
    @RequestMapping(value = "user_getTopicList", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParam(value = "用户id", name = "id", required = true, paramType = "query", dataType = "String")
    public String user_getTopicList(@RequestParam String id) {
        JSONObject jsonObject = new JSONObject();

        User_PageFenyeUtil user_pageFenyeUtil = new User_PageFenyeUtil();
        Map<String, Integer> fenye = user_pageFenyeUtil.Fenye(0, 2);//分页工具  pageSize 为o查询全部

        List<AbLuyoutaolunHuati> huatiList = userMapper.getUserTopicById(id, fenye);
        if (huatiList.size() > 0) {
            jsonObject.put("code", "200");
            jsonObject.put("message", "查询用户发布的话题成功");
            jsonObject.put("data", huatiList);
        } else {
            jsonObject.put("code", "300");
            jsonObject.put("message", "没有查询到用户发布的话题");
        }
        return jsonObject.toJSONString();
    }

    @ApiOperation(value = "获取用户有关的回复： 话题的评论(0) 评论的回复(1)")
    @RequestMapping(value = "user_getPinlun_Or_Huifu", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams(
            {@ApiImplicitParam(value = "用户id", name = "userid", required = true, paramType = "query", dataType = "String"),
                    @ApiImplicitParam(value = "话题的评论 输:0 评论的回复输:1", name = "type", required = true, paramType = "query", dataType = "String")})
    public String user_getPinlun_Or_Huifu(@RequestParam String userid, @RequestParam String type) {
        JSONObject jsonObject = new JSONObject();

        User_PageFenyeUtil user_pageFenyeUtil = new User_PageFenyeUtil();
        Map<String, Integer> fenye = user_pageFenyeUtil.Fenye(0, 0);//分页工具  pageSize 为o查询全部

        if (!type.equals("")) {
            if (type.equals("0")) {
                List<User_getPinlun> userPinlunList = userMapper.getUserPinglunUserByUserId(userid, fenye);
                if (userPinlunList.size() > 0) {
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "查询话题评论成功");
                    jsonObject.put("data", userPinlunList);
                } else {
                    jsonObject.put("code", "300");
                    jsonObject.put("message", "没有查询到用户的话题评论");
                }
            } else if (type.equals("1")) {

                List<User_getHuifu> userHuifuList = userMapper.getUserHuifuByUserId(userid, fenye);
                if (userHuifuList.size() > 0) {
                    jsonObject.put("code", "200");
                    jsonObject.put("message", "查询评论回复成功");
                    jsonObject.put("data", userHuifuList);
                } else {
                    jsonObject.put("code", "300");
                    jsonObject.put("message", "没有查询到用户的评论回复");
                }
            }
        }
        return jsonObject.toJSONString();
    }


    @ApiOperation(value = "用户删除什么(话题，评论,回复)话题（0）评论（1）回复（2）")
    @RequestMapping(value = "Delete_User_TopicOrPinlunOrHuaiti", method = RequestMethod.DELETE)
    @ApiImplicitParams(
            {@ApiImplicitParam(value = "用户id", name = "user_id", required = true, paramType = "query", dataType = "String"),
                    @ApiImplicitParam(value = "话题:0,评论:1,回复:2)", name = "who", required = true, paramType = "query", dataType = "int"),
                    @ApiImplicitParam(value = "话题/评论/回复的id", name = "id", required = true, paramType = "query", dataType = "int")})
    @ResponseBody
    public String Delete_User_TopicOrPinlunOrHuaiti(@RequestParam String user_id, @RequestParam Integer who, @RequestParam Integer id) {
        JSONObject jsonObject = new JSONObject();
        if (!user_id.equals("") && 0 <= who && who <= 2) {
            if (userMapper.DeleteUser_PinglunOrhuifuById(user_id, who.toString(), id)) {
                jsonObject.put("code", "200");
                jsonObject.put("message", "删除成功，");
            } else {
                jsonObject.put("code", "300");
                jsonObject.put("message", "删除失败");
            }
        } else {
            jsonObject.put("code", "303");
            jsonObject.put("message", "数据格式传入错误失败");
        }
        return jsonObject.toJSONString();
    }


    @ApiOperation(value = "获取所有用户 作测试， -》列表")
    @RequestMapping(value = "user_getAlluser", method = RequestMethod.GET)
    @ResponseBody
    public String user_getAlluser() {
        List<User> alluser = userMapper.getAllUser();
        String user_name = "";
        for (User user : alluser) {
            user_name += "==" + user.getUser_name();
        }
        return user_name;

    }
}
