package com.geekerstar.mybatis.plus.crud.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekerstar.mybatis.plus.crud.entity.People;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author geekerstar
 * date: 2019/12/19 20:54
 * description:
 */
public interface PeopleMapper extends BaseMapper<People> {

    /**
     * 自定义SQL查询，注解方式
     *
     * @param wrapper
     * @return
     */
    @Select("select * from people ${ew.customSqlSegment}")
    List<People> selectCustomize(@Param(Constants.WRAPPER) Wrapper<People> wrapper);

    /**
     * 自定义SQL查询，xml方式
     *
     * @param wrapper
     * @return
     */
    List<People> selectCustomize2(@Param(Constants.WRAPPER) Wrapper<People> wrapper);


    /**
     * 自定义分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<People> selectByPage(Page<People> page, @Param(Constants.WRAPPER) Wrapper<People> wrapper);
}
