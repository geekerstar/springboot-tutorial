package com.geekerstar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * date: 2019-08-21 09:54
 * description:
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String index(){
        return "hello world";
    }
}
