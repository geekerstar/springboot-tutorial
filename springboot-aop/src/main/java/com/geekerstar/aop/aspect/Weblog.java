package com.geekerstar.aop.aspect;

import java.lang.annotation.*;

/**
 * @author geekerstar
 * date: 2019-08-16 16:27
 * description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Weblog {
    /**
     * 日志描述信息
     * @return
     */
    String description() default "";
}
