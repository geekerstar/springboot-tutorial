package com.geekerstar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author geekerstar
 * date: 2019-08-21 14:13
 * description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.geekerstar.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("我是标题")
                // 描述
                .description("我是描述")
                // 服务条款网址
                .termsOfServiceUrl("https://www.geekerstar.com")
                // 版本号
                .version("1.0")
                // 联系方式
                .contact(new Contact("Geekerstar", "https://www.geekerstar.com", "247507792@qq.com"))
                .build();
    }
}
