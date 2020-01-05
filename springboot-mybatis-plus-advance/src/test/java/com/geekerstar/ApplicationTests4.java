package com.geekerstar;

import com.don.mp.config.MybatisPlusConfiguration;
import com.don.mp.dao.UserMapper;
import com.don.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests4 {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select() {
        MybatisPlusConfiguration.myTableName.set("user_2019");

        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void contextLoads() {
    }

}
