package com.geekerstar;

import com.geekerstar.mp.advance.dao.UserMapper;
import com.geekerstar.mp.advance.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests5 {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void deletedById() {
        int rows = userMapper.deleteAll();
        System.out.println(rows);
    }

    @Test
    public void insertBatchSomeColumn() {
        User user1 = User.builder().name("自动填充11").age(20).email("111@qq.com")
                .managerId(1094592041087729666L).build();
        User user2 = User.builder().name("自动填充22").age(20).email("111@qq.com")
                .managerId(1094592041087729666L).build();
        List<User> list = Arrays.asList(user1, user2);
        int rows = userMapper.insertBatchSomeColumn(list);
        System.out.println(rows);
    }

    @Test
    public void deleteByIdWithFill() {
        User user1 = User.builder().age(33).id(1171723746591907841L).build();
        int rows = userMapper.deleteByIdWithFill(user1);
        System.out.println(rows);
    }

    @Test
    public void alwaysUpdateSomeColumnById() {
        User user1 = User.builder().age(55).id(1171723746591907841L).name("更新不了").build();
        int rows = userMapper.alwaysUpdateSomeColumnById(user1);
        System.out.println(rows);
    }

    @Test
    public void contextLoads() {
    }

}
