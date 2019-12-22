package com.geekerstar.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekerstar.mybatis.plus.crud.CrudApplication;
import com.geekerstar.mybatis.plus.crud.entity.People;
import com.geekerstar.mybatis.plus.crud.mapper.PeopleMapper;
import lombok.extern.slf4j.Slf4j;
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
 * date: 2019/12/22 13:43
 * description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApplication.class)
public class SelectByWrapperMapperTest {

    @Resource
    private PeopleMapper peopleMapper;

    /**
     * selectOne()只能是返回one；
     * 否则报错Caused by: org.apache.ibatis.exceptions.TooManyResultsException:
     * Expected one result (or null) to be returned by selectOne(), but found: 2
     */
    @Test
    public void selectOne() {
        /*
         * 方式二选一
         */
        QueryWrapper<People> queryWrapper = new QueryWrapper<People>();
        QueryWrapper<People> queryWrapper1 = Wrappers.<People>query();
        queryWrapper.like("name", "d").between("age", 10, 20).isNotNull("email");

        People people = peopleMapper.selectOne(queryWrapper);
        log.info(" selectOne - " + people);
    }

    /**
     * 查询列表
     */
    @Test
    public void selectList() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").lt("age", 100);

        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::println);
    }

    /**
     * 查询数量
     */
    @Test
    public void selectCount() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "d").or().ge("age", 40).orderByDesc("age").orderByAsc("id");

        int result = peopleMapper.selectCount(queryWrapper);
        log.info(" selectCount - " + result);
    }

    /**
     * 根据条件构造查询
     */
    @Test
    public void selectObjs() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-01-14")
                .inSql("manager_id", "select id from people where name like '王%'");

        List<Object> list = peopleMapper.selectObjs(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 根据条件构造查询，单参数方式，这种会引发SQL注入
     */
    @Test
    public void selectObjs2() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        // 引发sql注入问题
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')='2019-01-14' or true or true")
                .inSql("manager_id", "select id from people where name like '王%'");

        List<Object> list = peopleMapper.selectObjs(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 根据条件构造器查询
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ? AND ( age < ? OR email IS NOT NULL )
     */
    @Test
    public void selectMaps() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));

        List<Map<String, Object>> list = peopleMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 分页查询，实际上执行两句，一句查总数，一句查数据
     * SELECT COUNT(1) FROM people WHERE name LIKE ? OR (age < ? AND age > ? AND email IS NOT NULL) ;
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ? OR ( age < ? AND age > ? AND email IS NOT NULL ) LIMIT ?,? ;
     */
    @Test
    public void selectPage() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));

        int result = peopleMapper.selectCount(queryWrapper);

        Page<People> page = new Page<People>(1, 2);
        IPage<People> peopleIPage = peopleMapper.selectPage(page, queryWrapper);
        log.info(" selectPage - " + peopleIPage);
        log.info(" getPages - " + peopleIPage.getPages());
        log.info(" getTotal - " + peopleIPage.getTotal());
        List<People> peopleList = peopleIPage.getRecords();
        peopleList.forEach(System.out::println);
    }

    /**
     * 分页查询，可以设置不查总记录数
     */
    @Test
    public void selectMapsPage() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        /**
         * SELECT COUNT(1) FROM people WHERE (age < ? OR email IS NOT NULL) AND name LIKE ? ;
         * SELECT id,name,age,email,manager_id,create_time FROM people WHERE ( age < ? OR email IS NOT NULL ) AND name LIKE ? LIMIT ?,? ;
         */
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
        Page<People> page = new Page<People>(1, 2);
        /**
         * 不查总记录数，只有一条sql语句
         * SELECT id,name,age,email,manager_id,create_time FROM people WHERE ( age < ? OR email IS NOT NULL ) AND name LIKE ? LIMIT ?,? ;
         */
        Page<People> page2 = new Page<People>(1, 2, false);
        IPage<Map<String, Object>> list = peopleMapper.selectMapsPage(page2, queryWrapper);
        log.info(" selectMapsPage - " + list.getRecords());
        log.info(" selectMapsPage - " + list);
        log.info(" getPages - " + list.getPages());
        log.info(" getTotal - " + list.getTotal());
        List<Map<String, Object>> peopleList = list.getRecords();
        peopleList.forEach(System.out::println);
    }

    /**
     * 自定义分页查询(解决多表分页)
     * SELECT COUNT(1) FROM people WHERE name LIKE ? ;
     * select * from people WHERE name LIKE ? LIMIT ?,? ;
     */
    @Test
    public void selectMyPage() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "d");
        Page<People> page = new Page<People>(1, 2);
        IPage<People> peopleIPage = peopleMapper.selectByPage(page, queryWrapper);
        log.info(" selectPage - " + peopleIPage);
        log.info(" getPages - " + peopleIPage.getPages());
        log.info(" getTotal - " + peopleIPage.getTotal());
        List<People> peopleList = peopleIPage.getRecords();
        peopleList.forEach(System.out::println);
    }

    /**
     * 条件构造，分页查询
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE age IN (?,?,?,?)
     */
    @Test
    public void selectMapsPage2() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 18));
        IPage<People> page = new Page<People>(1, 2);
        IPage<Map<String, Object>> list = peopleMapper.selectMapsPage(page, queryWrapper);
        log.info(" selectMapsPage - " + list.getRecords());
    }

    /**
     * 查询列表，条件构造
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE age IN (?,?,?,?) limit 1
     */
    @Test
    public void selectList2() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 18)).last("limit 1");
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    /**
     * SELECT id,name,age FROM people WHERE age IN (?,?,?,?) limit 1
     */
    @Test
    public void selectList3() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "age").in("age", Arrays.asList(30, 31, 34, 18)).last("limit 1");
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    /**
     * SELECT id,name,age,email FROM people WHERE age IN (?,?,?,?) limit 1
     */
    @Test
    public void selectList4() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 18)).last("limit 1")
                .select(People.class, info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id"));
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }


    /**
     * 公共条件构造
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ?
     */
    @Test
    public void testCondition() {
        String name = "d";
        String email = "";
        condition(name, email);
    }

    private void condition(String name, String email) {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.isNotEmpty(email)) {
            queryWrapper.like("email", email);
        }
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    /**
     * 公共条件构造，简写
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE name LIKE ?
     */
    @Test
    public void testCondition2() {
        String name = "d";
        String email = "";
        condition2(name, email);
    }

    private void condition2(String name, String email) {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), "name", name).like(StringUtils.isNotEmpty(email), "email", email);
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    /**
     * 条件构造查询
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE name = ? AND age IS NULL
     */
    @Test
    public void selectByWrapperAllEq() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "geek");
        map.put("age", null);
        queryWrapper.allEq(map);
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    /**
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE name = ?
     */
    @Test
    public void selectByWrapperAllEq2() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "geek");
        map.put("age", null);
        queryWrapper.allEq(map, false);
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

    /**
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE age IS NULL
     */
    @Test
    public void selectByWrapperAllEq3() {
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "geek");
        map.put("age", null);
        queryWrapper.allEq((k, v) -> !k.equals("name"), map);
        List<People> peopleList = peopleMapper.selectList(queryWrapper);
        peopleList.forEach(System.out::print);
    }

}
