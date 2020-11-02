package com.geekerstar.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.geekerstar.mybatis.plus.crud.CrudApplication;
import com.geekerstar.mybatis.plus.crud.entity.People;
import com.geekerstar.mybatis.plus.crud.service.IPeopleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019/12/19 21:09
 * description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApplication.class)
public class ServiceTest {

    @Autowired
    private IPeopleService iPeopleService;

    /**
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE age > ? ;
     * 只能查一条数据，查询多于一条报错
     * Caused by: org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 5
     */
    @Test
    public void getOne() {
        People people = iPeopleService.getOne(Wrappers.<People>lambdaQuery().gt(People::getAge, 39));
        System.out.println(people);
        log.info("IService getOne - " + people);
    }

    /**
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE age > ?
     * 查询多于一条警告
     * WARNWarn: execute Method There are  5 results.
     */
    @Test
    public void getOne2() {
        People people = iPeopleService.getOne(Wrappers.<People>lambdaQuery().gt(People::getAge, 20), false);
        System.out.println(people);
        log.info("IService getOne2 - " + people);
    }

    /**
     * 批量插入
     * DEBUG==>  Preparing: INSERT INTO people ( name, age, email ) VALUES ( ?, ?, ? )
     * DEBUG==> Parameters: batch1(String), 19(Integer), 792171677@qq.com(String)
     * DEBUG==> Parameters: batch2(String), 20(Integer), 792171678@qq.com(String)
     */
    @Test
    public void saveBatch() {
        People batch1 = People.builder().name("batch1").age(19).email("23123123@qq.com").build();
        People batch2 = People.builder().name("batch2").age(22).email("1231231312@qq.com").build();
        List<People> peopleList = Arrays.asList(batch1, batch2);
        boolean saveBatch = iPeopleService.saveBatch(peopleList);
        log.info(" IService saveBatch - " + saveBatch);
    }

    /**
     * 链式调用 查询
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE age > ? AND name LIKE ?
     */
    @Test
    public void chain() {
        List<People> peopleList = iPeopleService.lambdaQuery().gt(People::getAge, 10).like(People::getName, "d").list();
        peopleList.forEach(System.out::println);
    }

    /**
     * 链式调用 更新
     * UPDATE people SET age=? WHERE age = ?
     */
    @Test
    public void chain2() {
        boolean update = iPeopleService.lambdaUpdate().eq(People::getAge, 20).set(People::getAge, 22).update();
        log.info(" lambdaUpdate - " + update);
    }

    /**
     * 链式调用 删除
     * DELETE FROM people WHERE age = ?
     */
    @Test
    public void chain3() {
        boolean remove = iPeopleService.lambdaUpdate().eq(People::getAge, 22).remove();
        log.info(" lambdaUpdate - " + remove);
    }
}
