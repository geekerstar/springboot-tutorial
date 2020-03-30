package com.geekerstar.aop.limit.annotation;

import java.lang.annotation.*;

/**
 * @author geekerstar
 * @date 2020/3/30 16:13
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {

    String key() default "";
}
