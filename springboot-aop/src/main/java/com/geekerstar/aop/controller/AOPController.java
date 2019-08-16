package com.geekerstar.aop.controller;

import com.geekerstar.aop.form.UserForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * date: 2019-08-16 16:14
 * description:
 */
@RestController
public class AOPController {
    @GetMapping("/test")
    public String test(String name){
        return "hello AOP!";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserForm userForm){
        return "hello AOP2!";
    }
}
