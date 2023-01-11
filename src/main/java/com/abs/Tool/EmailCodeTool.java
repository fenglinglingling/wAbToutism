package com.abs.Tool;

import java.util.Random;

/**
 * 实现邮箱验证码
 * 1：通过ajax 将验证码发送到前端 进行比较
 */
public class EmailCodeTool {
        private final String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 生成验证码
      * @return random code
     */
    private String getIdentifyCode() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            char c = randString.charAt(random.nextInt(randString.length()));
            buffer.append(c);
        }
        return buffer.toString();
    }

    /**
     * 1：通过ajax 将验证码发送到前端 进行比较
     * @param toEmail XXX@qq.com
     * @return code 验证码
     */
    public String DoAjax(String toEmail){
        String title="当前操作的验证码";
        String content=getIdentifyCode();
        try {
            EmailTool.send(title,content,toEmail);
            System.out.println("验证码发送完成");
            System.out.println(content);
            return content;
        } catch (Exception e) {
            System.out.println("验证码发送失败");
            return null;
        }
    }

    }