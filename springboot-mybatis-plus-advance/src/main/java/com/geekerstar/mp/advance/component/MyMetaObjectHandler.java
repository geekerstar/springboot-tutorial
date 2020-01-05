package com.geekerstar.mp.advance.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author dyz
 * @program boot-use
 * @create 2019-09-11 14:09
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) {
            System.out.println("insertFill");
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateFill", metaObject);
        if (val == null) {
            System.out.println("updateFill");
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
