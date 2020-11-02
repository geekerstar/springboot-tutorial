package com.geekerstar.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.geekerstar.mybatis.plus.crud.CrudApplication;
import com.geekerstar.mybatis.plus.crud.entity.People;
import com.geekerstar.mybatis.plus.crud.mapper.PeopleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author geekerstar
 * date: 2019/12/22 14:02
 * description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApplication.class)
public class SelectMapperOtherTest {
    @Resource
    private PeopleMapper peopleMapper;

    /**
     * selectMaps使用场景 查询出其中几列（结果“{name=王天风, id=1088248166370832385}”，不存在null）
     */
    @Test
    public void selectMaps() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").lt("age", 40).select("id", "name");

        List<Map<String, Object>> list = peopleMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * SELECT avg(age) avg_age,min(age) min_age,max(age) max_age FROM people GROUP BY manager_id HAVING sum(age) < ?
     */
    @Test
    public void selectMaps2() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age").groupBy("manager_id").having("sum(age) < {0}", 500);
        List<Map<String, Object>> list = peopleMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 注意：是返回第一个字段的值. 如：
     * DEBUG==>  Preparing: SELECT id,name FROM people WHERE name LIKE ? AND age < ?
     * DEBUG==> Parameters: 王%(String), 40(Integer)
     * TRACE<==    Columns: id, name
     * TRACE<==        Row: 1088248166370832385, 王天风
     * DEBUG<==      Total: 1
     * 1088248166370832385
     * 查询两个字段，实际返回一个字段
     */
    @Test
    public void selectObjs() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").lt("age", 40).select("id", "name");

        List<Object> list = peopleMapper.selectObjs(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 不能加select列
     * SELECT COUNT( 1 ) FROM people WHERE name LIKE ? AND age < ?
     */
    @Test
    public void selectCount() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").lt("age", 40);

        Integer count = peopleMapper.selectCount(queryWrapper);
        log.info(" selectCount " + count);
    }

    /**
     * 返回的结果大于1条，则报错
     */
    @Test
    public void selectOne() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").lt("age", 40).select("id", "name");
        People people = peopleMapper.selectOne(queryWrapper);
        log.info(" selectOne " + people);
    }

    /**
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ? AND age < ?
     */
    @Test
    public void selectLambda() {
        // 实例化方法 三选一
        LambdaQueryWrapper<People> lambda = new QueryWrapper<People>().lambda();
        LambdaQueryWrapper<People> peopleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<People> peopleLambdaQueryWrapper1 = Wrappers.<People>lambdaQuery();

        lambda.like(People::getName, "d").lt(People::getAge, 40);

        List<People> list = peopleMapper.selectList(lambda);
        list.forEach(System.out::println);
    }

    /**
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ? AND ( age < ? OR email IS NOT NULL )
     */
    @Test
    public void selectLambda2() {
        LambdaQueryWrapper<People> peopleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        peopleLambdaQueryWrapper.likeRight(People::getName, "d").and(lqw -> lqw.lt(People::getAge, 40).or().isNotNull(People::getEmail));

        List<People> list = peopleMapper.selectList(peopleLambdaQueryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ? AND age >= ?
     */
    @Test
    public void selectLambda3() {
        List<People> peopleList = new LambdaQueryChainWrapper<People>(peopleMapper).like(People::getName, "d").ge(People::getAge, 20).list();

        peopleList.forEach(System.out::println);
    }

    /**
     * 自定义sql查询 测试
     * select * from people WHERE name LIKE ? AND ( age < ? OR email IS NOT NULL )
     */
    @Test
    public void selectCustomize() {
        LambdaQueryWrapper<People> peopleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        peopleLambdaQueryWrapper.likeRight(People::getName, "d").and(lqw -> lqw.lt(People::getAge, 40).or().isNotNull(People::getEmail));

        List<People> list = peopleMapper.selectCustomize(peopleLambdaQueryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 自定义sql查询 测试xml
     * select * from people WHERE name LIKE ? AND ( age < ? )
     */
    @Test
    public void selectCustomize2() {
        LambdaQueryWrapper<People> peopleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        peopleLambdaQueryWrapper.likeRight(People::getName, "geek").and(lqw -> lqw.lt(People::getAge, 30));

        List<People> list = peopleMapper.selectCustomize2(peopleLambdaQueryWrapper);
        list.forEach(System.out::println);
    }

}
