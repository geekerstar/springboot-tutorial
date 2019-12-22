package com.geekerstar.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
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

/**
 * @author geekerstar
 * date: 2019/12/22 13:34
 * description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApplication.class)
public class UpdateTest {

    @Resource
    private PeopleMapper peopleMapper;

    /**
     * 根据ID更新
     * UPDATE people SET age=?, email=? WHERE id=?
     */
    @Test
    public void updateById() {
        People people = People.builder().id(1087982257332887550L).age(19).email("e12ead@qq.com").build();
        int rows = peopleMapper.updateById(people);
        Assert.assertEquals(1, rows);
        log.info(" - updateById - " + rows);
    }

    /**
     * 根据条件构造器更新
     * UPDATE people SET age=?, email=? WHERE id = ?
     */
    @Test
    public void update() {
        UpdateWrapper<People> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 1087982257332887550L);
        People people = People.builder().id(1087982257332887550L).age(20).email("123456@qq.com").build();
        int rows = peopleMapper.update(people, updateWrapper);
        Assert.assertEquals(1, rows);
        log.info(" - update - " + rows);
    }

    /**
     * 根据条件构造器并组装where条件更新
     * UPDATE people SET email=? WHERE age=? AND id = ?
     */
    @Test
    public void update2() {
        People wherePeople = new People();
        wherePeople.setAge(20);
        UpdateWrapper<People> updateWrapper = new UpdateWrapper<>(wherePeople);
        updateWrapper.eq("id", 1087982257332887550L);
        People people = People.builder().email("1234567@qq.com").build();
        int rows = peopleMapper.update(people, updateWrapper);
        Assert.assertEquals(1, rows);
        log.info(" - update - " + rows);
    }


    /**
     * 修改少量的列
     * UPDATE people SET age=? WHERE id = ?
     */
    @Test
    public void update3() {
        UpdateWrapper<People> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", 1087982257332887550L).set("age", 21);
        int rows = peopleMapper.update(null, updateWrapper);
        Assert.assertEquals(1, rows);
        log.info(" - update - " + rows);
    }

    /**
     * 根据lambda条件构造器更新
     * UPDATE people SET age=? WHERE name = ? AND age = ?
     */
    @Test
    public void updateByWrapperLambda() {
        LambdaUpdateWrapper<People> lambdaUpdateWrapper = Wrappers.<People>lambdaUpdate();
        lambdaUpdateWrapper.eq(People::getName, "geekerstar").eq(People::getAge, 21).set(People::getAge, 20);
        int rows = peopleMapper.update(null, lambdaUpdateWrapper);
        Assert.assertEquals(1, rows);
        log.info(" - update - " + rows);
    }

    /**
     * 根据lambda条件构造器链式更新
     * UPDATE people SET age=? WHERE name = ? AND age = ?
     */
    @Test
    public void updateByWrapperLambdaChain() {
        boolean update = new LambdaUpdateChainWrapper<>(peopleMapper).eq(People::getName, "geekerstar").eq(People::getAge, 20).set(People::getAge, 19).update();
        log.info(" - updateByWrapperLambdaChain - " + update);
    }

}
