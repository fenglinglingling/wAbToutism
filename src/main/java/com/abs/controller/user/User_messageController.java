package com.abs.controller.user;

import com.abs.mapper.User_messageMapper;
import com.abs.pojo.Diqu;
import com.abs.pojo.User_message;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Api(tags = "用户留言管理")
@RestController
public class User_messageController {
    @Autowired
    private User_messageMapper userMessageMapper;


    ///   修改:.....//////////////////////////

//获取需要回复的留言List
    @ApiOperation(value = " 通过status状态查询需要解决的留言")
    @ResponseBody
    @RequestMapping(value = "/findAllStatusList", method = RequestMethod.GET)
    public String findAllStatusZero() {
        //查询问题，显示给前端
        JSONObject jsonObject = new JSONObject();
        List<User_message> allStatusZero = userMessageMapper.findAllStatusZero(0);
        if (allStatusZero != null) {
            jsonObject.put("code", "200");
            jsonObject.put("data", allStatusZero);
            jsonObject.put("message", "获取未解决留言 成功");

        } else {
            jsonObject.put("code", "300");
            jsonObject.put("message", "获取未解决留言 失败");
        }
        return jsonObject.toString();
    }


//用户添加留言
    @ApiOperation(value = "用户 添加留言")
    @RequestMapping(value = "/insertUserQuestion", method = RequestMethod.GET)
    @ResponseBody
    public String insertUserQuestion(User_message userMessage, HttpSession session) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("head", "留言");
        if (session.getAttribute("userId")!=null&& !session.getAttribute("userId").equals("")){
        if (userMessage.getUserQuestion() != null) {
            userMessage.setUserId((String) session.getAttribute("userId"));
            userMessage.setUserQuestion(userMessage.getUserQuestion());
            userMessage.setCreatetime(new Date());
            boolean b = userMessageMapper.insertUserQuestion(userMessage);
            if (b) {
                jsonObject.put("code", "200");
                jsonObject.put("message", "留言成功");
            } else {
                jsonObject.put("code", "300");
                jsonObject.put("message", "留言失败");
            }
        } else {
            jsonObject.put("code", "300");
            jsonObject.put("message", "必要数据缺失(question)请检查！");
        }
        }else {
            jsonObject.put("code", "201");
            jsonObject.put("message", "留言失败，请登录后重新留言");
        }
        return jsonObject.toJSONString();
    }


//客服进行留言回复
    @ApiOperation(value = "客服回复用户留言   需要 留言id 以及回复留言的内容")
    @RequestMapping(value = "/answerUserQuestion", method = RequestMethod.POST)
    @ResponseBody
    public String answerUserQuestion(String questionId, String replyContent) {
        JSONObject jsonObject = new JSONObject();
        System.out.println(questionId+"==="+replyContent);
        if (questionId != null && replyContent != null) {

            User_message user_message = new User_message();
            user_message.setId(Integer.valueOf(questionId));
            user_message.setManager_answer(replyContent);
            user_message.setReplyTime(new Date());
            user_message.setStatus(1);

            if (userMessageMapper.answerUserQuestion(user_message)) {
                jsonObject.put("code", "200");
                jsonObject.put("message", "留言回复");
            } else {
                jsonObject.put("code", "300");
                jsonObject.put("message", "留言回复失败");}

        } else {
            jsonObject.put("code", "300");
            jsonObject.put("message", "数据传入有误");
        }
        return jsonObject.toJSONString();

    }




    /**
     * 1.先通过问题的id将留言查找出来
     * 2.在将其展示给前端进行修改
     * 3.返回修改后的数据
     */
    @ApiOperation(value = "通过问题的id找到用户留言")
    @RequestMapping(value = "/findUserQuestionById", method = RequestMethod.GET)
    @ResponseBody
    public String findUserQuestionById(String questionId, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        if (questionId!=null){
            User_message user_message = userMessageMapper.selectUserQuestionById(Integer.valueOf(questionId));
            System.out.println(user_message);
//            request.setAttribute("user_message", user_message);
            jsonObject.put("code", "200");
            jsonObject.put("data", user_message);
            jsonObject.put("message", "查询成功");

        }
        return jsonObject.toJSONString();
    }

//    @ApiOperation(value = "删除用户留言    修改 ：建议为可以增加固定时间清除数据库")
//    @RequestMapping(value = "/deleteUserQuestion", method = RequestMethod.DELETE)
//    public void deleteUserQuestion(int id) {
//        boolean b = userMessageMapper.deleteUserQuestion(id);
//        if (b) {
//            System.out.println("删除成功");
//        } else {
//            System.out.println("删除失败");
//        }
//    }

}
