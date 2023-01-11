package com.abs.controller.user;

import com.abs.mapper.QuestionMapper;
import com.abs.pojo.Question;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "问题咨询")
@RestController
public class QuestionController {
    @Autowired
    private QuestionMapper questionMapper;

    @ApiOperation(value = "根据关键字（title）模糊查询所有问题")
    @ResponseBody
    @RequestMapping(value = "/queryQuestionByTitle",method = RequestMethod.GET)
    public String queryQuestionByTitle(String title){
//        String tit="%"+title+"%";
        // QuestionMapper 已修改
        JSONObject jsonObject = new JSONObject();
        if (title!=null) {
            List<Question> questionList = questionMapper.queryQuestionByTitle(title);
            jsonObject.put("head", "问题");

            if (questionList.size() > 0) {
                jsonObject.put("data", questionList);
                jsonObject.put("code", "200");
                jsonObject.put("message", "获取问题成功");
            } else {
                jsonObject.put("code", "300");
                jsonObject.put("message", "没有获取到问题");
            }
        }
        return jsonObject.toString();
    }


     //这里的数据库 查询还要修改
    @ApiOperation(value = "查询所有问题   用户点开询问聊天框 系统返回给前端的热点问题    ")
    @RequestMapping(value = "/queryAllQuestion",method = RequestMethod.GET)
    @ResponseBody
    public String queryAllQuestion(){
        JSONObject jsonObject = new JSONObject();
        List<Question> questionList = questionMapper.queryAllQuestion();
        if (questionList!=null){
            jsonObject.put("data",questionList);
            jsonObject.put("code","200");
            jsonObject.put("message","获取问题成功");
        }else {
            jsonObject.put("code","300");
            jsonObject.put("message","获取问题失败");}
        return jsonObject.toJSONString();
    }

//    @ApiOperation(value = "根据关键字（title）修改问题")
//    @ResponseBody
//    @RequestMapping(value = "/updateQuestion",method = RequestMethod.PUT)
//    public String updateQuestion(Question question, HttpServletRequest request){
//        //查询问题，显示给前端
//        List<Question> questionList = questionMapper.queryQuestionByTitle("1234");
//        request.setAttribute("questionList",questionList);
//
//        //根据搜索到的问题，选择问题进行修改
//        boolean ques= questionMapper.updateQuestion(question);
//        JSONObject jo = new JSONObject();
//        jo.put("questionList",ques);
//
//        if (ques){
//            System.out.println("修改成功");
//        }else {
//            System.out.println("修改失败");
//        }
//        return jo.toString();
//    }
//
    @ApiOperation(value = "添加问题")
    @RequestMapping(value = "/insertQuestion",method = RequestMethod.POST)
    public void InsertQuestion(Question question){
        boolean b = questionMapper.insertQuestion(question);
        if (b){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }
//
//    @ApiOperation(value = "删除问题")
//    @RequestMapping(value = "/deleteQuestion_1",method = RequestMethod.DELETE)
//    public void testDelQuestion( String title){
//        boolean b = questionMapper.delQuestion(title);
//        if (b){
//            System.out.println("删除成功");
//        }else {
//            System.out.println("删除失败");
//        }
//    }

}
