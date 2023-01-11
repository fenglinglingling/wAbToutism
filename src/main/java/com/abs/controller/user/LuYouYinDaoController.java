package com.abs.controller.user;

import com.abs.mapper.LuYouYinDaoMapper;
import com.abs.pojo.*;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "旅游引导")
public class LuYouYinDaoController {
    @Autowired
    private LuYouYinDaoMapper luYouYinDao;

    @ResponseBody
    @ApiOperation("查询6个主页的景区")
    @RequestMapping(value = {"/selectAllJingQus"}, method = RequestMethod.GET)
    public String selectAllJingQus() {
        List<AbLuyouyindaoJingqu> jingqus = luYouYinDao.selectAllJingQus();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jingqus", jingqus);
        return jsonObject.toString();
    }


    @ResponseBody
    @ApiOperation("通过景点标签进行筛选")
    @RequestMapping(value = {"/selectJingQusByConditions"}, method = RequestMethod.GET)
    public String selectJingQusByConditions(String types) {
        List<AbLuyouyindaoJingqu> jingqus = luYouYinDao.selectJingQusByConditions(types);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jingqus", jingqus);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("根据景区名进行查询")
    @RequestMapping(value = {"/selectJingQusByName"}, method = RequestMethod.GET)
    public String selectJingQusByName(String name) {
        List<AbLuyouyindaoJingqu> jingqus = luYouYinDao.selectJingQusByName(name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jingqus", jingqus);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("根据景区id进行查询")
    @RequestMapping(value = {"/selectJingQusById"}, method = RequestMethod.GET)
    public String selectJingQusById(Integer id) {
        List<AbLuyouyindaoJingqu> jingqus = luYouYinDao.selectJingQusById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jingqus", jingqus);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("查询全部群聊")
    @RequestMapping(value = {"/selectAllGroups"}, method = RequestMethod.GET)
    public String selectAllGroups() {
        List<AbLuyouyindaoChatgroup> groups = luYouYinDao.selectAllGroups();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groups", groups);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过用户id查询他的信息")
    @RequestMapping(value = {"/selectUserById"}, method = RequestMethod.GET)
    public String selectUserById(String id) {
        System.out.println(id);
        AbUser abUser = luYouYinDao.selectUserById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abUser", abUser);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("通过群号id查询")
    @RequestMapping(value = {"/selectGroupById"}, method = RequestMethod.GET)
    public String selectGroupById(Integer id) {
        AbLuyouyindaoChatgroup group = luYouYinDao.selectGroupById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("group", group);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("添加一个聊天群(房间)")
    @RequestMapping(value = {"/addGroup"}, method = RequestMethod.POST)
    public String addGroup(AbLuyouyindaoChatgroup chatgroup) {
        boolean b = luYouYinDao.addGroup(chatgroup);
        String re = "添加一个聊天群(房间)失败";
        if (b) {
            re = "添加一个聊天群(房间)成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addGroup_result", re);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("添加一次聊天信息")
    @RequestMapping(value = {"/addChat"}, method = RequestMethod.POST)
    public String addChat(AbLuyouyindaoChat chat, HttpServletRequest request) throws ParseException {
        Object userId = request.getSession().getAttribute("userId");
        Integer groupId = (Integer) request.getSession().getAttribute("groupId");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        Date time = df.parse(date);
        chat.setTime(time);
        chat.setUserId((String) userId);
        boolean b = luYouYinDao.addChat(chat);
        AbLuyouyindaoChatlianxi lianxi = new AbLuyouyindaoChatlianxi();
        Integer maxId = luYouYinDao.getMaxId();
        System.out.println(maxId);
        lianxi.setChatId(maxId);
        lianxi.setGroupId(groupId);
        String re = "添加一次聊天信息失败";
        if (b == true) {
            re = "添加一次聊天信息成功";
        }
        boolean b1 = luYouYinDao.addLianxi(lianxi);
        if (b1 == true) {
            re = "添加一次聊天信息成功";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addChat_result", re);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("查看该聊天群的信息")
    @RequestMapping(value = {"/selectChatGroup"}, method = RequestMethod.GET)
    public String selectChatGroup(HttpServletRequest request) {
        Integer groupId = (Integer) request.getSession().getAttribute("groupId");
        AbLuyouyindaoChatgroup chatgroup = luYouYinDao.selectChatGroup(groupId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("chatgroup", chatgroup);
        return jsonObject.toString();
    }

    @ResponseBody
    @ApiOperation("查找某个聊天群的所有聊天并按时间升序排序(默认)")
    @RequestMapping(value = {"/selectChatByGroupId"}, method = RequestMethod.GET)
    public String selectChatByGroupId(Integer id, HttpServletRequest request) {
        Integer groupId = (Integer) request.getSession().getAttribute("groupId");
        Object userId = request.getSession().getAttribute("userId");
        List<AbLuyouyindaoChat> chats = luYouYinDao.selectChatByGroupId(groupId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("chats", chats);
        jsonObject.put("userId", Integer.parseInt(userId.toString()));
        return jsonObject.toString();
    }



    @ApiOperation(value = "个人管理_我的路线")
    @ResponseBody
    @RequestMapping(value = "selectAllLuXian", method = RequestMethod.GET)
    public String selectAllLuXian(int page, int limit, HttpSession session) {
        Object userId = session.getAttribute("userId");
        int begin = (page - 1) * page;
        if (begin < 0) {
            begin = 0;
        }
        JSONObject jsonObject = new JSONObject();
        List<AbLuyouyindaoLuxian> luxians = luYouYinDao.selectAllLuXian(begin, limit,userId.toString());
        int count = luYouYinDao.selectCount();
        jsonObject.put("count", count);
        jsonObject.put("data", luxians);
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        return jsonObject.toString();
    }


    @ApiOperation(value = "删除我的路线")
    @ResponseBody
    @RequestMapping(value = "/delLuXian", method = RequestMethod.DELETE)
    public String delLuXian(String[] ids) {
        int k = 0;
        int code = 500;
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        for (int i = 0; i < ids.length; i++) {
            boolean b = luYouYinDao.deleteById(Integer.parseInt(ids[i]));
            if (b) {
                k += 1;
            }
        }
        if (k == ids.length) {
            code = 200;
        }
        jsonObject.put("code", code);
        return jsonObject.toString();
    }
}
