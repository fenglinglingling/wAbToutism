package com.abs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.abs.config")
public class TourismApplication {
    public static void main(String[] args) {
        SpringApplication.run(TourismApplication.class, args);
    }
//    http://localhost:8080/swagger-ui.html
//    git测试
//    追踪过的文件也可以不用添加到暂存区，直接提交也行！
//    用hot-fix分支来测试
//    master合并冲突demo 同样 记得先提交
//    hot-fix 合并冲突dome 改完记得先提交
}
