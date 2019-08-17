package com.geekerstar.controller;

import com.geekerstar.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * date: 2019-08-17 09:16
 * description:
 */
@RestController
public class TimeController {

    @PostMapping("/time")
    public String createTime(@RequestBody User user){
        System.out.println(user.getCreateTime().toString());
        return "success";
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        System.out.println(user.getCreateTime().toString());
        // 将User以JSON格式返回
        return user;
    }
}
