package com.geekerstar.service;

import com.geekerstar.entity.User;
import com.geekerstar.mapper.UserExMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserExMapper userExMapper;

    public List<User> getUsers() {
        return userExMapper.getUsers();
    }
}
