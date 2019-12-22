package com.geekerstar.dao;

import com.geekerstar.mybatis.plus.crud.CrudApplication;
import com.geekerstar.mybatis.plus.crud.entity.People;
import com.geekerstar.mybatis.plus.crud.mapper.PeopleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019/12/22 13:30
 * description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApplication.class)
public class MapperTest {

    @Resource
    private PeopleMapper peopleMapper;

    /**
     * 查询全部列表
     */
    @Test
    public void select() {
        List<People> peopleList = peopleMapper.selectList(null);
        Assert.assertEquals(5, peopleList.size());
        peopleList.forEach(System.out::println);
    }

    /**
     * 插入
     */
    @Test
    public void insert() {
        People people = new People(1087982257332887555L, "12133", 18, "1231sadd@qq.com", null, LocalDateTime.now(),"备注");
        int result = peopleMapper.insert(people);
        Assert.assertEquals(1, result);
        log.info(" PeopleMapperTest insert -- " + result);
    }
}
