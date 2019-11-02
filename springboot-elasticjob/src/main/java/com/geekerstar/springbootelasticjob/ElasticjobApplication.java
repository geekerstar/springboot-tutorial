package com.geekerstar.springbootelasticjob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.geekerstar.springbootelasticjob.dao")

public class ElasticjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticjobApplication.class, args);
    }

}
