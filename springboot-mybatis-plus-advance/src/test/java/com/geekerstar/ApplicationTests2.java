package com.geekerstar;

import com.don.mp.dao.UserMapper;
import com.don.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests2 {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = User.builder().name("自动填充").age(20).email("111@qq.com")
                .managerId(1094592041087729666L).build();
        int rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void updateById() {
        User user = User.builder().name("自动填充").age(22).email("111@qq.com")
                .id(1094592041087729666L).build();
        int rows = userMapper.updateById(user);
        System.out.println(rows);
    }


    @Test
    public void contextLoads() {
    }

}
