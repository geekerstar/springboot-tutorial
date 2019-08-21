package com.geekerstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author geekerstar
 * date: 2019-08-21 11:49
 * description:
 */
@SpringBootApplication
public class RunnerApplication {
    public static void main(String[] args) {
        System.out.println("服务开始……");
        SpringApplication.run(RunnerApplication.class,args);
        System.out.println("服务结束……");

    }
}
