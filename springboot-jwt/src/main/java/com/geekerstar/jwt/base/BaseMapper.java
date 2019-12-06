package com.geekerstar.jwt.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description 自己的Mapper基类，主要不能放到mapper下
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T>{
}
