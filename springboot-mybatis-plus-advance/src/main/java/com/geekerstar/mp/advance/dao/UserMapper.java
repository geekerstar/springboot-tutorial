package com.geekerstar.mp.advance.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.geekerstar.mp.advance.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author geekerstar
 * @date 2020/11/2 17:33
 * @description
 */
public interface UserMapper extends BaseMapper<User> {

    @SqlParser(filter = true) // 多租户
    @Select("select * from user ${ew.customSqlSegment}")
    List<User> mySelectList(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    int deleteAll();

    int insertBatchSomeColumn(List<User> list);

    int deleteByIdWithFill(User user);

    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) User user);

}
