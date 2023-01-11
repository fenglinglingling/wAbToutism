package com.abs.controller.admin;

import com.abs.pojo.AbLuyoutaolunComment;
import com.abs.pojo.AbLuyoutaolunHuati;
import com.abs.pojo.AbLuyoutaolunHuifu;
import com.abs.mapper.HuatiMapper;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(tags = "讨论区管理")
public class HuatiController {
    @Autowired
    private HuatiMapper huatiMapper;


    @ApiOperation("查询所有话题")
    @ResponseBody
    @RequestMapping(value = "getAllHuati-houtai",produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    public String getAllHuati(int page, int limit){
        System.out.println(page+"--"+limit);
        JSONObject jsonObject=new JSONObject();

        int begin = (page - 1) * limit;
        if (begin < 0) {
            begin=0;
        }

        List<AbLuyoutaolunHuati> list=huatiMapper.getAllHuati(begin, limit);
        for (int i=0;i<list.size();i++){
            //为每个类的like赋值
            list.get(i).setLike(huatiMapper.getHuatiLike(list.get(i).getTopicId()));
        }
        int count=huatiMapper.getCount();
        System.out.println(count);
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", count);
        jsonObject.put("data", list);
        return jsonObject.toString();
    }
    //通过文章id批量删除文章数据
    @ApiOperation("批量删除文章")
    @ResponseBody
    @RequestMapping(value = "huati-batchDelete-houtai", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public String deleteHuatiByBatch(String topicId) {
        JSONObject jsonObject = new JSONObject();
        System.out.println(topicId);

        try {
            int count = 0;
            String[] ids = topicId.split(",");
            for (int m = 1; m < ids.length; m++) {
                huatiMapper.deleteHuati(ids[m]);
                huatiMapper.deleteHuatiLike(ids[m]);
                List<AbLuyoutaolunComment> abLuyoutaolunComments = huatiMapper.querryCommentBytopicId(ids[m]);
                for (int i=0;i<abLuyoutaolunComments.size();i++){
                    huatiMapper.deleteComment(abLuyoutaolunComments.get(i).getCommentsId());
                    huatiMapper.deleteCommentLike(abLuyoutaolunComments.get(i).getCommentsId());
                    List<AbLuyoutaolunHuifu> abLuyoutaolunHuifus = huatiMapper.querryHuifuByCommentId(abLuyoutaolunComments.get(i).getCommentsId());
                    for (int x=0;x<abLuyoutaolunHuifus.size();x++){
                        huatiMapper.deleteHuifu(abLuyoutaolunHuifus.get(x).getResponseId());
                        huatiMapper.deleteHuifuLike(abLuyoutaolunHuifus.get(x).getResponseId());
                        jsonObject.put("code","200");
                    }

                }
                    count++;
            }
            jsonObject.put("code", 200);
            jsonObject.put("count", count);
        } catch (Exception ex) {
            jsonObject.put("code", 500);
        }

        return jsonObject.toString();
    }
    @ApiOperation("删除文章")
    @ResponseBody
    @RequestMapping(value = "huati-delete-houtai", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public String deleteHuati(String topicId) {
        JSONObject jsonObject = new JSONObject();
        huatiMapper.deleteHuati(topicId);
        huatiMapper.deleteHuatiLike(topicId);
        jsonObject.put("code","500");
        List<AbLuyoutaolunComment> abLuyoutaolunComments = huatiMapper.querryCommentBytopicId(topicId);
        for (int i=0;i<abLuyoutaolunComments.size();i++){
            huatiMapper.deleteComment(abLuyoutaolunComments.get(i).getCommentsId());
            huatiMapper.deleteCommentLike(abLuyoutaolunComments.get(i).getCommentsId());
            List<AbLuyoutaolunHuifu> abLuyoutaolunHuifus = huatiMapper.querryHuifuByCommentId(abLuyoutaolunComments.get(i).getCommentsId());
            for (int x=0;x<abLuyoutaolunHuifus.size();x++){
                huatiMapper.deleteHuifu(abLuyoutaolunHuifus.get(x).getResponseId());
                huatiMapper.deleteHuifuLike(abLuyoutaolunHuifus.get(x).getResponseId());
                jsonObject.put("code","200");
            }

        }




        return jsonObject.toString();
    }
    @ApiOperation("查询数据表格中选中的文章id")
    @ResponseBody
    @RequestMapping(value = "gettopicIdByAdmin-houtai",produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    public String gettopicIdByAdmin(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("topicId",request.getSession().getAttribute("topicIdByAdmin"));
        return jsonObject.toString();
    }
    @ApiOperation("通过文章查找该文章下的所有评论")
    @ResponseBody
    @RequestMapping(value = "getAllComment-houtai",produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    public String getAllCommentByAdmin(int page, int limit,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        Object topicId=request.getSession().getAttribute("topicIdByAdmin");

        int begin = (page - 1) * limit;
        if (begin < 0) {
            begin=0;
        }
        List<AbLuyoutaolunComment> abLuyoutaolunComments = huatiMapper.querryCommentBytopicIdAndPage(begin, limit, topicId.toString());
        for (int i=0;i<abLuyoutaolunComments.size();i++){
            abLuyoutaolunComments.get(i).setLike(huatiMapper.getCommentLike(abLuyoutaolunComments.get(i).getCommentsId()));
        }
        int count=huatiMapper.getCommentCount(topicId.toString());
        System.out.println(count);
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", count);
        jsonObject.put("data", abLuyoutaolunComments);
        return jsonObject.toString();
    }
    @ApiOperation("删除评论")
    @ResponseBody
    @RequestMapping(value = "comment-delete-houtai", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public String deleteComment(Integer commentId) {
        JSONObject jsonObject = new JSONObject();
        huatiMapper.deleteComment(commentId);
        huatiMapper.deleteCommentLike(commentId);
        jsonObject.put("code","500");
        List<AbLuyoutaolunHuifu> abLuyoutaolunHuifus = huatiMapper.querryHuifuByCommentId(commentId);
        for(int i=0;i<abLuyoutaolunHuifus.size();i++){
            huatiMapper.deleteHuifu(abLuyoutaolunHuifus.get(i).getResponseId());
            huatiMapper.deleteHuifuLike(abLuyoutaolunHuifus.get(i).getResponseId());
            jsonObject.put("code","200");
        }
        return jsonObject.toString();
    }
    //通过评论id批量删除评论数据
    @ApiOperation("批量删除文章")
    @ResponseBody
    @RequestMapping(value = "comment-batchDelete-houtai", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public String deleteCommentByBatch(String commentId) {
        JSONObject jsonObject = new JSONObject();

        try {
            int count = 0;
            String[] ids = commentId.split(",");
            for (int m = 1; m < ids.length; m++) {
                huatiMapper.deleteComment(Integer.parseInt(ids[m]));
                huatiMapper.deleteCommentLike(Integer.parseInt(ids[m]));
                List<AbLuyoutaolunHuifu> abLuyoutaolunHuifus = huatiMapper.querryHuifuByCommentId(Integer.parseInt(ids[m]));
                for(int i=0;i<abLuyoutaolunHuifus.size();i++){
                    huatiMapper.deleteHuifu(abLuyoutaolunHuifus.get(i).getResponseId());
                    huatiMapper.deleteHuifuLike(abLuyoutaolunHuifus.get(i).getResponseId());
                    jsonObject.put("code","200");
                }
                count++;
            }
            jsonObject.put("code", 200);
            jsonObject.put("count", count);
        } catch (Exception ex) {
            jsonObject.put("code", 500);
        }

        return jsonObject.toString();
    }
    @ApiOperation("通过评论查找该评论下的所有回复")
    @ResponseBody
    @RequestMapping(value = "getAllHuifu-houtai",produces = "application/json;charset=UTF-8",method = RequestMethod.GET)
    public String getAllHuifuByAdmin(int page, int limit,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        Object commentId=request.getSession().getAttribute("commentIdByAdmin");

        int begin = (page - 1) * limit;
        if (begin < 0) {
            begin=0;
        }
        List<AbLuyoutaolunHuifu> abLuyoutaolunHuifus = huatiMapper.querryHuifuBycommentIdAndPage(begin, limit, commentId.toString());
        for (int i=0;i<abLuyoutaolunHuifus.size();i++){
            abLuyoutaolunHuifus.get(i).setLike(huatiMapper.getHuifuLike(abLuyoutaolunHuifus.get(i).getResponseId()));
        }
        int count=huatiMapper.getHuifuCount(commentId.toString());
        System.out.println(count);
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", count);
        jsonObject.put("data", abLuyoutaolunHuifus);
        return jsonObject.toString();
    }
    @ApiOperation("删除回复")
    @ResponseBody
    @RequestMapping(value = "huifu-delete-houtai", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public String deleteHuifu(Integer responseId) {
        JSONObject jsonObject = new JSONObject();
        huatiMapper.deleteHuifu(responseId);
        huatiMapper.deleteHuifuLike(responseId);
        jsonObject.put("code","200");
        return jsonObject.toString();
    }
    //通过回复id批量删除回复数据
    @ApiOperation("批量删除回复")
    @ResponseBody
    @RequestMapping(value = "huifu-batchDelete-houtai", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public String deleteHuifuByBatch(String responseId) {
        JSONObject jsonObject = new JSONObject();

        try {
            int count = 0;
            String[] ids = responseId.split(",");
            for (int m = 1; m < ids.length; m++) {
                huatiMapper.deleteHuifu(Integer.parseInt(ids[m]));
                huatiMapper.deleteHuifuLike(Integer.parseInt(ids[m]));
                count++;
            }
            jsonObject.put("code", 200);
            jsonObject.put("count", count);
        } catch (Exception ex) {
            jsonObject.put("code", 500);
        }

        return jsonObject.toString();
    }


}
