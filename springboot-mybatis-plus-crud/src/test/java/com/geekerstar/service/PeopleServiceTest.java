package com.geekerstar.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geekerstar.mybatis.plus.crud.CrudApplication;
import com.geekerstar.mybatis.plus.crud.entity.People;
import com.geekerstar.mybatis.plus.crud.mapper.PeopleMapper;
import com.geekerstar.mybatis.plus.crud.service.IPeopleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author geekerstar
 * date: 2019/12/19 21:09
 * description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApplication.class)
public class PeopleServiceTest {

    @Autowired
    private IPeopleService iPeopleService;

    @Resource
    private PeopleMapper peopleMapper;

    @Test
    public void getOne(){
        People people = iPeopleService.getOne(Wrappers.<People>lambdaQuery().gt(People::getAge, 20));
        log.info(" IService getOne - " + people);
    }

    @Test
    public void selectById() {
        People people = peopleMapper.selectById(1087982257332887551L);
        log.info(" selectById - " + people);
    }
}
