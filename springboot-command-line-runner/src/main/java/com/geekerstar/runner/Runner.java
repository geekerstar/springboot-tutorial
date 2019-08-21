package com.geekerstar.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author geekerstar
 * date: 2019-08-21 11:49
 * description:
 */
@Component
public class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner 开始初始化……");
    }
}
