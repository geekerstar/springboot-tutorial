package com.geekerstar.mybatisplus.model;

import lombok.Data;

/**
 * @author geekerstar
 * date: 2019/12/10 21:34
 * description:
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
