package com.geekerstar.redis;

import com.geekerstar.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * date: 2019-08-20 12:00
 * description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("aaa","bbb");
        Assert.assertEquals("bbb",stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws InterruptedException {
        User user = new User("22@qq.com","aaa","ddd","sds","22");
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        operations.set("com.geekerstar",user);
        operations.set("com.geekerstar.redis",user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        boolean exists = redisTemplate.hasKey("com.geekerstar.redis");
        if (exists){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }


}
