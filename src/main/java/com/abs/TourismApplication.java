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
}
