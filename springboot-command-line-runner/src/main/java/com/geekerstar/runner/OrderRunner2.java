package com.geekerstar.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author geekerstar
 * date: 2019-08-21 11:51
 * description:
 */
@Component
@Order(2)
public class OrderRunner2 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("OrderRunner2开始初始化……");

    }
}
