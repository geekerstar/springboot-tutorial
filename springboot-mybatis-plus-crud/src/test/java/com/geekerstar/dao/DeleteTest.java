package com.geekerstar.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import java.util.Map;

/**
 * @author geekerstar
 * date: 2019/12/22 13:19
 * description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApplication.class)
public class DeleteTest {

    @Resource
    private PeopleMapper peopleMapper;

    /**
     * 根据id删除
     * DELETE FROM people WHERE id=?
     */
    @Test
    public void deleteById(){
        int rows = peopleMapper.deleteById(1208617644090568706L);
        Assert.assertEquals(1,rows);
        log.info(" - deleteById - " + rows);
    }

    /**
     * 根据map删除
     * DELETE FROM people WHERE id = ? AND age = ?
     */
    @Test
    public void deleteByMap(){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("id",1208617417686245377L);
        columnMap.put("age",22);
        int rows = peopleMapper.deleteByMap(columnMap);
        Assert.assertEquals(1, rows);
        log.info(" - deleteByMap - " + rows);
    }

    /**
     * 根据id批量删除
     * DELETE FROM people WHERE id IN ( ? , ? )
     */
    @Test
    public void deleteBatchIds(){
        int rows = peopleMapper.deleteBatchIds(Arrays.asList(1208609579114967042L, 1087982257332887559L));
        Assert.assertEquals(2, rows);
        log.info(" - deleteBatchIds - " + rows);
    }

    /**
     * 利用条件构造器删除
     * DELETE FROM people WHERE age = ? OR age > ?
     */
    @Test
    public void delete(){
        LambdaQueryWrapper<People> lambdaQueryWrapper = Wrappers.<People>lambdaQuery();
        lambdaQueryWrapper.eq(People::getAge, 18).or().gt(People::getAge, 99);
        int rows = peopleMapper.delete(lambdaQueryWrapper);
        Assert.assertEquals(1, rows);
        log.info(" - delete - " + rows);
    }
}
