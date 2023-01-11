package com.abs.controller;

import com.abs.pojo.AbCultureActivity;
import com.abs.pojo.AbCultureFestival;
import com.abs.pojo.AbCultureHeritage;
import com.abs.pojo.AbUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//仅供参考，可以删除！！！！！！！！！！！！！！！！！！！！！！！！

@Controller
public class LoginController {

    @RequestMapping({"/", "/index1"})
    public String login(HttpSession session) {
        return "/index";
    }

    @RequestMapping({"/", "/index"})//默认地址
    public String zhuye(HttpSession session) {
        Object userId = session.getAttribute("userId");
        return "/index0";
    }


    @RequestMapping({"/toLoginPage"})
    public String toLoginPage() {
        return "/login";
    }

    @RequestMapping({"/toRegisterPage"})
    public String toregistPage() {
        return "/Register";
    }

    @RequestMapping("/toReMenDiQu")
    public String toReMenDiQu(HttpSession session) {
        return "/remendiqu";
    }

    @RequestMapping("/toDiQuMeiShi")
    public String toDiQuMeiShi(HttpSession session) {
        return "/diqumeishi";
    }

    @RequestMapping("/blog-submit")
    public String blogSidebar() {
        return "/lvyoutaolun_fabuhuati";
    }

    @RequestMapping("/blog-standard")
    public String blogStandard() {
        return "/lvyoutaolun_huati";
    }

    @RequestMapping("/blog-details")
    public String blogDetails() {
        return "/lvyoutaolun_xiangqing";
    }

    @RequestMapping({"/luyouyindao"})
    public String toluyouyindao() {
        return "/luyouyindao";
    }

    @RequestMapping({"/luyouyindao_qunliao"})
    public String toluyouyindao_qunliao() {
        return "/lvyouyindao_Index";
    }

    @RequestMapping({"/luyouyindao_group"})
    public String toluyouyindao_group(Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("groupId", id);
        System.out.println(id);
        return "/qunliao-group";
    }

    //历史民族
    @RequestMapping({"/history_details"})
    public String history_details() {
        return "/history_details";
    }

    //风土人情
    @RequestMapping({"/culture_customs"})
    public String culture_customs() {
        return "/culture_customs";
    }

    //藏羌文化
    @RequestMapping({"/culture_wenhua"})
    public String culture_wenhua() {
        return "/culture_wenhua";
    }

    //非遗天地
    @RequestMapping({"/culture_intangible_heritage"})
    public String culture_intangible_heritage() {
        return "/culture_intangible_heritage";
    }

    //文艺动态
    @RequestMapping({"/culture_art"})
    public String culture_art() {
        return "/culture_art";
    }

    //文化视频
    @RequestMapping({"/culture_video"})
    public String culture_video() {
        return "/culture_video";
    }

    //特色文化主页
    @RequestMapping({"/culture_main"})
    public String showHistoryCulture() {
        System.out.println("fasdfasdfasdfasdfasdf");
        return "/culture_history";
    }

    //跳旅游住宿主页
    @RequestMapping({"/LvKeMain"})
    public String LvKeMain() {
        System.out.println("=======1=======");
        return "/lvke_main";
    }

    //跳旅游住宿主详情
    @RequestMapping({"/LvKeDetails"})
    public String LvKeDetails(String name) {
        System.out.println("=======2=======" + name);
        return "/lvke_details";
    }
//    http://localhost:8080/culture/LvKeDetails?name=123

    //跳旅游住宿搜索
    @RequestMapping(value = "/LvKeDetailsSearch/{whereto}", method = RequestMethod.GET)
    public String LvKeDetailsSearch(@PathVariable String whereto) {
        System.out.println("==============" + whereto);
        return "/lxke_zhusu_search";
    }

    //后台
    @RequestMapping(value = "/houtaiAdmin")
    public String toAdmin() {
        return "/houtai/admin";
    }

    @RequestMapping(value = "/houtaiUser")
    public String toUser(HttpServletRequest request) {
        System.out.println(request.getSession().getAttribute("userId"));
        return "/houtai/user";
    }

    //后台咨询
    @RequestMapping(value = "/houtaiZiXun")
    public String houtaiZiXun() {
        return "/houtai/houtaiZixun";
    }

    @RequestMapping(value = "/ziXunReply")
    public String ziXunReply() {
        return "/houtai/ziXunReply";
    }

    //    谭浪
//管理员
/*//跳转到管理员界面
@RequestMapping({"/list-blog-admin"})
public String blogByAdmin() {
    return "/houtai/admin";
}*/
    //跳转到话题管理界面
    @RequestMapping({"/list-blogDetail-admin"})
    public String blogDetailByAdmin() {
        return "/houtai/list-huati";
    }

    //跳转到评论管理界面
    @RequestMapping({"/list-comment-admin"})
    public String commentByAdmin(String topicId, HttpServletRequest request) {
        request.getSession().setAttribute("topicIdByAdmin", topicId);
        return "/houtai/list-comment";
    }

    //跳转到回复管理界面
    @RequestMapping({"/list-huifu-admin"})
    public String huifuByAdmin(String commentId, HttpServletRequest request) {
        request.getSession().setAttribute("commentIdByAdmin", commentId);
        return "/houtai/list-huifu";
    }

    //跳转到个人信息界面
    @RequestMapping({"/list-user"})
    public String listUser() {
        return "/houtai/user";
    }

    //跳转到我的话题界面
    @RequestMapping({"/list-huati-user"})
    public String listHuatiByUser() {
        return "/houtai/myHuati";
    }

    //跳转到我的浏览记录界面
    @RequestMapping({"/list-record-user"})
    public String listRecordByUser() {
        return "/houtai/myRecord";
    }
//    李进兴
/*@RequestMapping({"/toHoutai"})
public String toHoutai() {
    return "/houtai/guanli";
}*/

    //后台-热门地区
    @RequestMapping({"/houtai_remendiqu"})
    public String houtai_remendiqu() {
        return "/houtai/remendiqu";
    }


    @RequestMapping({"/houtai_remendiqu_add"})
    public String houtai_remendiqu_add() {
        return "/houtai/remendiqu/add";
    }


    @RequestMapping({"/houtai_remendiqu_update"})
    public String houtai_remendiqu_update(int updateDiQuId, HttpSession session) {
        session.setAttribute("updateDiQuId", updateDiQuId);
        return "/houtai/remendiqu/update";
    }

 /*   //个人——
    @RequestMapping({"/toGeRen"})
    public String toGeRen() {
        return "/houtai/geren";
    }*/

    @RequestMapping({"/toLuXian"})
    public String toLuXian() {
        return "/houtai/luxian";
    }

//    刘鑫
//用户信息管理

    //用户信息列表展示
    @RequestMapping({"/userDataList"})
    public String userDataList() {
        return "/houtai/userDataList";
    }

    //添加用户信息
    @RequestMapping({"/user_add"})
    public String user_add() {
        return "/houtai/user_add";
    }

    //通过id查找用户的信息进行渲染
    @RequestMapping(value = "/getUpdateUserId",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String getUpdateUserId(String id) {
        System.out.println(id);
        AbUser.USERID=id;
//        AbUser user = userInfoMapper.findUpdateUser(id);
        return "/houtai/user_edit";
    }

    //通过id查找用户的信息进行渲染
    @RequestMapping(value = "/infoUser",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String infoUser(String id) {
        System.out.println(id);
        AbUser.USERID=id;
//        AbUser user = userInfoMapper.findUpdateUser(id);
        return "/houtai/user_info";
    }


    //更换头像
    @RequestMapping(value = "/exchangeImg",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String exchangeImg() {
        return "/houtai/uploadTouImg";
    }



    //跳转到活动详情页面
    @RequestMapping(value = "/cultureActivityInfo",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String cultureActivityInfo(String name) {
        System.out.println(name);
        AbCultureActivity.ActivityName=name;
        return "/culture_activity_info";
    }
    //跳转到非遗详情页面
    @RequestMapping(value = "/cultureHerigateInfo",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String cultureHerigateInfo(Integer id) {
        System.out.println(id);
        AbCultureHeritage.HerigateId=id;
        return "/culture_herigate_info";
    }

    //跳转到非遗详情页面
    @RequestMapping(value = "/festivalInfo",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String festivalInfo(String name) {
        System.out.println(name);
        AbCultureFestival.FestivalName=name;
        return "/culture_festival_info";
    }

    //后台-住宿
    @RequestMapping({"/houtai_zhusu"})
    public String houtai_zhusu() {return "/houtai/ZhuSu/Zhusu";}
    //后台-add
    @RequestMapping("/houtai_zhusu_add")
    public String houtai_zhusu_add() {
        return "/houtai/ZhuSu/add";
    }
    @RequestMapping("/houtai_zhusu_uptate")
    public String houtai_zhusu_info() {
        return "/houtai/ZhuSu/update";
    }

    //跳转到用户的信息页面
    @RequestMapping(value = "/loginUserInfo",produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String loginUserInfo() {
        return "/houtai/loginUserInfo";
    }



}
