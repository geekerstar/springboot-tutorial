package com.geekerstar.mybatis.plus.crud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geekerstar
 * date: 2019/12/19 20:30
 * description:
 */
@SpringBootApplication
@MapperScan("com.geekerstar.mybatis.plus.crud.mapper")
public class CrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }
}
