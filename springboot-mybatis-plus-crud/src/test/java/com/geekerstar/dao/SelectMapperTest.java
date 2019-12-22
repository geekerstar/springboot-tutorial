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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author geekerstar
 * date: 2019/12/22 14:09
 * description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApplication.class)
public class SelectMapperTest {
    @Resource
    private PeopleMapper peopleMapper;

    @Test
    public void selectById() {
        People people = peopleMapper.selectById(1087982257332887555L);
        log.info(" selectById - " + people);
    }

    @Test
    public void selectBatchIds() {
        List<People> peopleList = peopleMapper.selectBatchIds(Arrays.asList(1087982257332887555L,1087982257332887554L));
        Assert.assertEquals(2, peopleList.size());
        peopleList.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("name","dyz");
        columnMap.put("manager_id",null);
        List<People> peopleList = peopleMapper.selectByMap(columnMap);
        Assert.assertEquals(1, peopleList.size());
        peopleList.forEach(System.out::println);
    }

}
