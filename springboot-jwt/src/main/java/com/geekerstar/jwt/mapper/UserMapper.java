package com.geekerstar.jwt.mapper;

import com.geekerstar.jwt.base.BaseMapper;
import com.geekerstar.jwt.entity.User;
import com.github.pagehelper.Page;


public interface UserMapper extends BaseMapper<User> {

    Page<User> getUsers();
}
