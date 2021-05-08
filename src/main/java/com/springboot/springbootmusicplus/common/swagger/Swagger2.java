package com.springboot.springbootmusicplus.common.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linliquan
 * @description:
 * @create 2021/5/6 11:50
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${swagger.enable:true}")
    private boolean enableSwagger;


    @Bean
    public Docket createRestApi() {

        List<Parameter> pars = new ArrayList<>(1);
//        Parameter tokenId =
//                new ParameterBuilder()
//                        .name("token")
//                        .modelRef(new ModelRef("string"))
//                        .parameterType("header")
//                        .required(true).build();
//
//        pars.add(tokenId);

        return new Docket(DocumentationType.SWAGGER_2)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                .pathMapping("/")
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.springboot.springbootmusicplus.controller"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                // 是否启用Swagger
                .enable(enableSwagger);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("轻语音乐 API")
                .description("轻语音乐 swagger地址：http://localhost:8088/swagger-ui.html  主页地址：http://localhost:8088/index.html")
                .termsOfServiceUrl("http://www.linliquan.top")
                .version("1.0")
                .build();
    }
}
