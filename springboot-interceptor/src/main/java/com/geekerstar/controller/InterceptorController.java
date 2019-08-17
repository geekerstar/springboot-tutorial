package com.geekerstar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * date: 2019-08-17 10:01
 * description:
 */
@RestController
public class InterceptorController {

    @GetMapping("/admin/test")
    public String test(){
        System.out.println("test");
        return "success";
    }
}
