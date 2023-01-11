package com.abs.controller.admin;


import com.abs.mapper.ZiXunMapper;
import com.abs.pojo.AbZixunQuestions;
import com.abs.pojo.AbZixunReply;
import com.abs.pojo.Question;
import com.abs.service.AdminService;
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
@Api(tags = "咨询管理")
@RequestMapping("/ziXun")
public class ZiXunController {
    @Autowired
    private ZiXunMapper ziXunMapper;

    @Autowired
    private AdminService adminService;

    /*@ApiOperation("查询所有的问题")
    @RequestMapping(value = "getAllQuestion", method = RequestMethod.GET)
    public String getAllQuestion() {
        List<AbZixunQuestions> allQuestion = ziXunMapper.getAllQuestion();
        JSONObject object = new JSONObject();
        object.put("allQuestion", allQuestion);
        return object.toString();
    } */
    @ApiOperation("查询所有的问题")
    @RequestMapping(value = "getAllQuestion", produces = {"application/json;charset=utf-8"}, method = RequestMethod.GET)
    public String getAllQuestion(int page, int limit) {
        List<AbZixunQuestions> allQuestion = adminService.getAllQuestion(page, limit);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", 0);
        jsonObject.put("count", ziXunMapper.getCount());
        jsonObject.put("data", allQuestion);
        return jsonObject.toString();
    }

    @ApiOperation("查询所有的问题")
    @RequestMapping(value = "getKeyQuestion", produces = {"application/json;charset=utf-8"}, method = RequestMethod.GET)
    public String getKeyQuestion(int page, int limit, String key) {
        List<AbZixunQuestions> allQuestion = adminService.getKeyQuestion(page, limit, key);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", 0);
        jsonObject.put("count", ziXunMapper.getCountByKey(key));
        jsonObject.put("data", allQuestion);
        return jsonObject.toString();
    }

    @ApiOperation("根据类型查询问题")
    @RequestMapping(value = "getQuestionByType", method = RequestMethod.GET)
    public String getQuestionByType(Integer atr) {
        List<AbZixunQuestions> allQuestion = ziXunMapper.getQuestionByType(atr);
        JSONObject object = new JSONObject();
        object.put("allQuestion", allQuestion);
        return object.toString();
    }

    @ApiOperation("查询所有的回复")
    @RequestMapping(value = "getAllReply", method = RequestMethod.GET)
    public String getAllReply() {
        List<AbZixunReply> allReply = ziXunMapper.getAllReply();
        JSONObject object = new JSONObject();
        object.put("allQuestion", allReply);
        return object.toString();
    }

    @ApiOperation("通过问题id查询回复")
    @RequestMapping(value = "getReplyByQuestion", method = RequestMethod.GET)
    public String getReplyByQuestion(Integer atr) {
        AbZixunReply replyByQuestion = ziXunMapper.getReplyByQuestion(atr);
        JSONObject object = new JSONObject();
        object.put("replyByQuestion", replyByQuestion);
        return object.toString();
    }

    //添加时，给del添加上id号，并添加该id对应的点击函数，点击之后可以出发删除功能（加个弹窗）
    @ApiOperation("删除问题")
    @RequestMapping(value = "deleteQuestion", method = RequestMethod.DELETE)
    public String deleteQuestion(Integer atr) {
        System.out.println(atr);
        JSONObject object = new JSONObject();
        AbZixunReply replyByQuestion = ziXunMapper.getReplyByQuestion(atr);
        AbZixunQuestions questionById = ziXunMapper.getQuestionById(atr);
        ziXunMapper.deleteQuestion(atr);
        ziXunMapper.deleteReplyByQuestion(atr);
        ziXunMapper.deleteQRWithJQR(questionById.getContent(), replyByQuestion.getReplyContent());
        return object.toString();
    }

    @ApiOperation("删除回复")
    @RequestMapping(value = "deleteReply", method = RequestMethod.DELETE)
    public String deleteReply(Integer atr) {
        JSONObject object = new JSONObject();
        AbZixunReply replyByQuestion = ziXunMapper.getReplyByQuestion(atr);
        if (replyByQuestion != null){
            System.out.println(1);
            if (ziXunMapper.deleteReply(atr)) {
                System.out.println(2);
                object.put("msg", "success");
            } else {
                System.out.println(3);
                object.put("msg", "false");
            }
        } else{
            System.out.println(4);
            object.put("msg", "false");
        }
        return object.toString();
    }

    @ApiOperation("添加一个回复")
    @RequestMapping(value = "savaReply", method = RequestMethod.POST)
    public String savaReply(AbZixunReply reply, HttpSession session) {
        JSONObject object = new JSONObject();
        try {
            boolean flag = true;
            flag = ziXunMapper.savaReply(reply);
            flag = ziXunMapper.updateQuestionType(reply.getQuestionId());
            AbZixunQuestions questionById = ziXunMapper.getQuestionById(reply.getQuestionId());
            flag = ziXunMapper.addQRtoJQR((String) session.getAttribute("userId"), questionById.getContent(), reply.getReplyContent());
            if (flag) {
                object.put("msg", "success");
            }
        } catch (Exception e) {
            object.put("msg", "false");
        }
        return object.toString();
    }

    //获取表单内容，封装好用ajax发送给后端，并重新获取数据，加载新的内容
    @ApiOperation("添加一个问题")
    @RequestMapping(value = "savaQuestion", method = RequestMethod.POST)
    public String savaQuestion(AbZixunQuestions questions) {
        questions.setType(0);
        JSONObject object = new JSONObject();
        if (ziXunMapper.savaQuestion(questions)) {
            object.put("msg", "添加成功");
        } else {
            object.put("msg", "添加成功");
        }

        return object.toString();
    }

    //机器人留言
    @ApiOperation("添加一个问题")
    @RequestMapping(value = "savaQuestion2", method = RequestMethod.GET)
    public String savaQuestion2(String content) {
        AbZixunQuestions question = new AbZixunQuestions();
        question.setType(0);
        question.setContent(content);
        JSONObject object = new JSONObject();
        if (ziXunMapper.savaQuestion(question)) {
            object.put("msg", "添加成功");
            object.put("code", 200);
            object.put("head", "留言");
        } else {
            object.put("msg", "添加成功");
        }

        return object.toString();
    }
}
