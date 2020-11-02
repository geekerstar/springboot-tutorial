package com.geekerstar.mp.advance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geekerstar
 * @date 2020/11/2 17:33
 * @description
 */
@SpringBootApplication
@MapperScan("com.geekerstar.mp.advance.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
