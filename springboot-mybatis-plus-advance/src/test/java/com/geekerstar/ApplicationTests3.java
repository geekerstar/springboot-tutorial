package com.geekerstar;


import com.geekerstar.mp.advance.dao.UserMapper;
import com.geekerstar.mp.advance.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests3 {

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
        User res = userMapper.selectById(1171669303842385921L);

        User user = User.builder().name("自动填充").age(22).email("111@baomidou.com")
                .id(1171669303842385921L).version(res.getVersion()).build();
        int rows = userMapper.updateById(user);
        System.out.println(rows);
    }


    @Test
    public void contextLoads() {
    }

}
