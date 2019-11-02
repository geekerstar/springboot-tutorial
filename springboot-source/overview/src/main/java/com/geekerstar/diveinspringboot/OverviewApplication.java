package com.geekerstar.diveinspringboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@ServletComponentScan(basePackages = "com.imooc.diveinspringboot.web.servlet")
public class OverviewApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(OverviewApplication.class)
//				.web(WebApplicationType.NONE)
                .run(args);
//		SpringApplication.run(DiveInSpringBootApplication.class, args);
    }
}
