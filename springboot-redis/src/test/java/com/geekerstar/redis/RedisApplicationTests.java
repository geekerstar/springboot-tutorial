package com.geekerstar.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author geekerstar
 * date: 2019-08-20 11:59
 * description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("hello redis");

    }
}
