package com.geekerstar.dao;

import com.geekerstar.mybatis.plus.crud.CrudApplication;
import com.geekerstar.mybatis.plus.crud.entity.People;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author geekerstar
 * date: 2019/12/22 12:56
 * description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApplication.class)
public class ARTest {

    /**
     * 插入
     * INSERT INTO people ( id, name, age, email, create_time ) VALUES ( ?, ?, ?, ?, ? )
     */
    @Test
    public void insert(){
        People people = People.builder().id(1087982257332887559L).name("AR").age(19).email("2323232@qq.com").createTime(LocalDateTime.now()).build();
        boolean insert = people.insert();
        log.info(" - AR Insert - " + insert);
    }

    /**
     * 根据ID查询
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE id=?
     */
    @Test
    public void selectById(){
        People people = People.builder().build();
        People selectPeople = people.selectById(1087982257332887559L);
        // 测试是否为同一个对象，false不是同一个对象
        System.out.println(people == selectPeople);
        System.out.println(selectPeople);
        log.info(" - AR selectById - " + selectPeople);
    }

    /**
     * 根据ID更新
     * UPDATE people SET name=?, age=?, email=?, create_time=? WHERE id=?
     */
    @Test
    public void updateById(){
        People people = People.builder().id(1087982257332887559L).name("AR").age(19).email("247507792@qq.com").createTime(LocalDateTime.now()).build();
        boolean update = people.updateById();
        log.info(" - AR updateById - " + update);
    }

    /**
     * 根据ID删除
     * DELETE FROM people WHERE id=?
     */
    @Test
    public void deleteById(){
        People people = People.builder().id(1087982257332887559L).build();
        boolean delete = people.deleteById();
        log.info(" - AR deleteById - " + delete);
    }


    /**
     * 插入或更新
     * 执行两条语句
     * SELECT id,name,age,email,manager_id,create_time FROM people WHERE id=? ;
     * INSERT INTO people ( id, name, age, email, create_time ) VALUES ( ?, ?, ?, ?, ? ) ;
     * 这个方法就是保存操作，如果数据库中无此id，则为insert插入操作，如果数据库中有此id，则相当于根据此id修改操作
     */
    @Test
    public void insertOrUpdate(){
        People people = People.builder().id(1087982257332887559L).name("AR1").age(19).email("123123123123@qq.com").createTime(LocalDateTime.now()).build();
        boolean insertOrUpdate = people.insertOrUpdate();
        log.info(" - AR insertOrUpdate - " + insertOrUpdate);
    }

    /**
     * 插入自动生成id
     * INSERT INTO people ( id, name, age, email, create_time ) VALUES ( ?, ?, ?, ?, ? )
     */
    @Test
    public void insertAutoId(){
        People people = People.builder().name("ARauto").age(22).email("23231@142.com").createTime(LocalDateTime.now()).build();
        boolean insert = people.insert();
        log.info(" - AR insertAutoId - " + insert);
        log.info(" - AR insertAutoId - " + people.getId());
    }

}
