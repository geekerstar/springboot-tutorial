package com.geekerstar.shiro;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@SpringBootApplication(scanBasePackages = "com.geekerstar")
@EntityScan("com.geekerstar.shiro.domain")
public class ShiroApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }

    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }

}
