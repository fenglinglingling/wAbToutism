package com.abs.pojo;

import lombok.Data;

@Data
public class User {
    private String user_id ;//主键 自增
    private String user_phone ;//电话
    private String user_name;//昵称
    private String user_password;//密码
    private String user_role;//用户角色 user/admin
    private String user_gender;//性别
    private String user_instruction;// 个人说明
    private String user_image;// 个人说明
    private String user_email;// 个人邮箱

}
