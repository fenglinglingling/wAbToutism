package com.abs.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//不要改动！！！！！！！！！！！！！！！！！！！！！！！！！
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo())
                .groupName("abTourismSystem")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.abs"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("abTourismSystem").description("abTourism").version("V1.0").build();
    }

}
