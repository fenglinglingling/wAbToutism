package com.abs.controller.user;

import com.abs.mapper.LuYouTaoLunMapper;
import com.abs.pojo.*;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "话题讨论")
public class HuaTiTaoLunController {
    @Autowired
    private LuYouTaoLunMapper luYouTaoLun;
    @ResponseBody
    @ApiOperation("获取当前账号的id")
    @RequestMapping(value = { "/getUserId"},method = RequestMethod.GET)
    public String getUserId(HttpServletRequest request){
        Object userId = request.getSession().getAttribute("userId");

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userId",userId.toString());
        return jsonObject.toString();
    }


    @ResponseBody
    @ApiOperation("查询所有的话题按时间进行降序")
    @RequestMapping(value = { "/selectHuaTis"},method = RequestMethod.GET)
    public String selectHuaTis(){
        ArrayList<AbLuyoutaolunHuati> huatis = luYouTaoLun.selectHuaTis();
        System.out.println(huatis);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("huatis",huatis);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("查询话题发布者的信息")
    @RequestMapping(value = { "/selectHuaTisFromUser"},method = RequestMethod.GET)
    public String selectHuaTisFromUser(String id){
        AbUser abUser = luYouTaoLun.selectHuaTisFromUser(id);
        System.out.println(abUser);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("abUser",abUser);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过话题的id来进行查询")
    @RequestMapping(value = { "/selectHuatiById"},method = RequestMethod.GET)
    public String selectById(Integer id){
        AbLuyoutaolunHuati huati = luYouTaoLun.selectById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("huati", huati);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("根据话题的title来进行查询")
    @RequestMapping(value = { "/selectByTitle"},method = RequestMethod.GET)
    public String selectByTitle(String title){
        List<AbLuyoutaolunHuati> huatis = luYouTaoLun.selectByTitle(title);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("huatis", huatis);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过话题的id来查询它的评论")
    @RequestMapping(value = { "/selectCommentByHuatiId"},method = RequestMethod.GET)
    public String selectCommentByHuatiId(Integer topic_id){
        List<AbLuyoutaolunComment> comments = luYouTaoLun.selectCommentByHuatiId(topic_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("comments", comments);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过评论的id来查询出他的回复")
    @RequestMapping(value = { "/selectHuiFuByCommentId"},method = RequestMethod.GET)
    public String selectHuiFuByCommentId(Integer comments_id){
        List<AbLuyoutaolunHuifu> huifus = luYouTaoLun.selectHuiFuByCommentId(comments_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("huifus", huifus);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过评论的id来查询出评论信息")
    @RequestMapping(value = { "/selectCommentByCommentId"},method = RequestMethod.GET)
    public String selectCommentByCommentId(Integer comments_id){
        AbLuyoutaolunComment abLuyoutaolunComment = luYouTaoLun.selectCommentByCommentId(comments_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("comment", abLuyoutaolunComment);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过回复的id来查询出回复信息")
    @RequestMapping(value = { "/selectHuifuByResponseId"},method = RequestMethod.GET)
    public String selectHuifuByResponseId(Integer response_id){
        AbLuyoutaolunHuifu abLuyoutaolunHuifu = luYouTaoLun.selectHuifuByResponseId(response_id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("huifu", abLuyoutaolunHuifu);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("添加一个话题")
    @RequestMapping(value = { "/addHuati"},method = RequestMethod.POST)
    public String addHuati(AbLuyoutaolunHuati huati){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String Time=df.format(new Date());// new Date()为获取当前系统时间
        try {
            Date date = df.parse(Time);
            huati.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(huati);
        boolean b = luYouTaoLun.addHuati(huati);
        String re = "添加一个话题失败";
        if (b == true){
            re = "添加一个话题成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addHuati_result", re);
        return jsonObject.toString();
    }


    @ResponseBody
    @ApiOperation("添加一个话题评论")
    @RequestMapping(value = { "/addComment"},method = RequestMethod.POST)
    public String addComment(AbLuyoutaolunComment comment){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String Time=df.format(new Date());// new Date()为获取当前系统时间
        try {
            Date date = df.parse(Time);
            comment.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean b = luYouTaoLun.addComment(comment);
        String re = "添加一个话题评论失败";
        if (b == true){
            re = "添加一个话题评论成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addComment_result", re);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("添加一个评论回复")
    @RequestMapping(value = { "/addCommentHuiFu"},method = RequestMethod.POST)
    public String addCommentHuiFu(AbLuyoutaolunHuifu huifu){
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//        huifu.setTime(dateFormat.format(date));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String Time=df.format(new Date());// new Date()为获取当前系统时间
        try {
            Date date = df.parse(Time);
            huifu.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean b = luYouTaoLun.addCommentHuiFu(huifu);
        String re = "添加一个评论回复失败";
        if (b == true){
            re = "添加一个评论回复成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addCommentHuiFu_result", re);
        return jsonObject.toString();
    }

//    @ResponseBody
//    @ApiOperation("给一个话题添加一个点赞(改)")
//    @RequestMapping(value = { "/addHuaTiLike"},method = RequestMethod.PUT)
//    public String addHuaTiLike(Integer topic_id){
//        boolean b = luYouTaoLun.addHuaTiLike(topic_id);
//        String re = "给一个话题添加一个点赞失败";
//        if (b == true){
//            re = "给一个话题添加一个点赞成功";
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("addHuaTiLike_result", re);
//        return jsonObject.toString();
//    }

    @ResponseBody
    @ApiOperation("给一个话题添加一个点赞")
    @RequestMapping(value = { "/addHuaTiLike"},method = RequestMethod.POST)
    public String addHuaTiLike(String userId,Integer topicId){
        boolean b = luYouTaoLun.addHuaTiLike(userId,topicId);
        String re = "给一个话题添加一个点赞失败";
        if (b == true){
            re = "给一个话题添加一个点赞成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addHuaTiLike_result", re);
        return jsonObject.toString();
    }
    @ResponseBody
    @ApiOperation("查询话题共有点赞数")
    @RequestMapping(value = { "/queryHuaTilike"},method = RequestMethod.GET)
    public String queryHuaTilike(Integer topicId){
        Integer integer = luYouTaoLun.queryHuaTilike(topicId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("huatilike", integer);
        return jsonObject.toString();
    }
    @ResponseBody
    @ApiOperation("查询话题是否能被点赞")
    @RequestMapping(value = { "/queryHuaTilike1"},method = RequestMethod.GET)
    public String queryHuaTilike1(String userId,Integer topicId){
        Integer integer = luYouTaoLun.queryHuaTilike1(userId,topicId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("huatilike", integer);
        return jsonObject.toString();
    }
    @ResponseBody
    @ApiOperation("删除一个话题点赞")
    @RequestMapping(value = { "/deleteHuaTilike"},method = RequestMethod.DELETE)
    public String deleteHuaTilike(String userId,Integer topicId){
        boolean b = luYouTaoLun.deleteHuaTilike(userId,topicId);
        String re = "删除一个点赞失败";
        if (b == true){
            re = "删除一个点赞成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addHuaTiLike_result", re);
        return jsonObject.toString();
    }


//    @ResponseBody
//    @ApiOperation("给一个评论进行一个点赞")
//    @RequestMapping(value = { "/addCommentLike"},method = RequestMethod.PUT)
//    public String addCommentLike(Integer comments_id){
//        boolean b = luYouTaoLun.addCommentLike(comments_id);
//        String re = "给一个评论进行一个点赞失败";
//        if (b == true){
//            re = "给一个评论进行一个点赞成功";
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("addCommentLike_result", re);
//        return jsonObject.toString();
//    }
    @ResponseBody
    @ApiOperation("给一个评论进行一个点赞")
    @RequestMapping(value = { "/addCommentLike"},method = RequestMethod.POST)
    public String addCommentLike(String userId,Integer commentId){
        boolean b = luYouTaoLun.addCommentLike(userId,commentId);
        String re = "给一个评论进行一个点赞失败";
        if (b == true){
            re = "给一个评论进行一个点赞成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addCommentLike_result", re);
        return jsonObject.toString();
    }
    @ResponseBody
    @ApiOperation("查询评论共有点赞数")
    @RequestMapping(value = { "/queryCommentlike"},method = RequestMethod.GET)
    public String queryCommentlike(Integer commentId){
        Integer integer = luYouTaoLun.queryCommentlike(commentId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commentlike", integer);
        return jsonObject.toString();
    }
    @ResponseBody
    @ApiOperation("查询评论是否能被点赞")
    @RequestMapping(value = { "/queryCommentlike1"},method = RequestMethod.GET)
    public String queryCommentlike1(String userId,Integer commentId){
        Integer integer = luYouTaoLun.queryCommentlike1(userId,commentId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commentlike", integer);
        return jsonObject.toString();
    }
    @ResponseBody
    @ApiOperation("删除一个评论点赞")
    @RequestMapping(value = { "/deleteCommentlike"},method = RequestMethod.DELETE)
    public String deleteCommentlike(String userId,Integer commentId){
        boolean b = luYouTaoLun.deleteCommentlike(userId,commentId);
        String re = "删除一个点赞失败";
        if (b == true){
            re = "删除一个点赞成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addHuaTiLike_result", re);
        return jsonObject.toString();
    }




//    @ResponseBody
//    @ApiOperation("给一个回复进行一个点赞")
//    @RequestMapping(value = { "/addHuiFuLike"},method = RequestMethod.PUT)
//    public String addHuiFuLike(Integer response_id){
//        boolean b = luYouTaoLun.addHuiFuLike(response_id);
//        String re = "给一个回复进行一个点赞失败";
//        if (b == true){
//            re = "给一个回复进行一个点赞成功";
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("addHuiFuLike_result",re);
//        return jsonObject.toString();
//    }
    @ResponseBody
    @ApiOperation("给一个回复进行一个点赞")
    @RequestMapping(value = { "/addHuiFuLike"},method = RequestMethod.POST)
    public String addHuiFuLike(String userId,Integer responseId){
        boolean b = luYouTaoLun.addHuiFuLike(userId,responseId);
        String re = "给一个回复进行一个点赞失败";
        if (b == true){
            re = "给一个回复进行一个点赞成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addHuiFuLike_result",re);
        return jsonObject.toString();
    }
    @ResponseBody
    @ApiOperation("查询回复共有点赞数")
    @RequestMapping(value = { "/queryResponselike"},method = RequestMethod.GET)
    public String queryResponselike(Integer responseId){
        Integer integer = luYouTaoLun.queryResponselike(responseId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("responselike", integer);
        return jsonObject.toString();
    }
    @ResponseBody
    @ApiOperation("查询回复是否能被点赞")
    @RequestMapping(value = { "/queryResponselike1"},method = RequestMethod.GET)
    public String queryResponselike1(String userId,Integer responseId){
        Integer integer = luYouTaoLun.queryResponselike1(userId,responseId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("responselike", integer);
        return jsonObject.toString();
    }
    @ResponseBody
    @ApiOperation("删除一个回复点赞")
    @RequestMapping(value = { "/deleteResponselike"},method = RequestMethod.DELETE)
    public String deleteResponselike(String userId,Integer responseId){
        boolean b = luYouTaoLun.deleteResponselike(userId,responseId);
        String re = "删除一个点赞失败";
        if (b == true){
            re = "删除一个点赞成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addHuaTiLike_result", re);
        return jsonObject.toString();
    }




    @ResponseBody
    @ApiOperation("根据话题id添加一次浏览次数")
    @RequestMapping(value = { "/addHuatiRead"},method = RequestMethod.PUT)
    public String addHuatiRead(Integer topic_id){
        boolean b = luYouTaoLun.addHuatiRead(topic_id);
        String re = "根据话题id添加一次浏览次数失败";
        if (b == true){
            re = "根据话题id添加一次浏览次数成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addHuatiRead_result", re);
        return jsonObject.toString();
    }

    //添加浏览记录
    @ResponseBody
    @ApiOperation("添加浏览记录")
    @RequestMapping(value = { "/addHuatiRecord"},method = RequestMethod.PUT)
    public String addHuatiRecord(Integer topicId,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        Object userId = request.getSession().getAttribute("userId");
        AbluyoutaolunRecord record=new AbluyoutaolunRecord();
        record.setUserId(userId.toString());
        record.setTopicId(topicId);
        //获取时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String Time=df.format(new Date());// new Date()为获取当前系统时间
        record.setTime(Time);
        if (luYouTaoLun.deleteHuatiRecord(record)){
            luYouTaoLun.addHuatiRecord(record);
        }else {
            luYouTaoLun.addHuatiRecord(record);
        }

        return jsonObject.toString();
    }



}
