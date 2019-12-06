package com.geekerstar.jwt.service;


import com.geekerstar.jwt.entity.User;
import com.geekerstar.jwt.mapper.UserMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Page<User> getUsers() {
        return userMapper.getUsers();
    }

    public User selectById(long id){
        return userMapper.selectByPrimaryKey(id);
    }
}
