package com.geekerstar.springbootelasticjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.geekerstar.springbootelasticjob.dao")

public class SpringbootElasticjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootElasticjobApplication.class, args);
    }

}
