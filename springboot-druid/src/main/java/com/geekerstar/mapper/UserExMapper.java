package com.geekerstar.mapper;

import com.geekerstar.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserExMapper {

    List<User> getUsers();
}
