package com.geekerstar.diveinspringboot.repository;

import com.geekerstar.diveinspringboot.annotation.SecondLevelRepository;
import com.geekerstar.diveinspringboot.annotation.FirstLevelRepository;

/**
 * 我的 {@link FirstLevelRepository}
 *
 * @author 小马哥
 * @since 2018/5/14
 */
@SecondLevelRepository(value = "myFirstLevelRepository") // Bean 名称
public class MyFirstLevelRepository {
}
