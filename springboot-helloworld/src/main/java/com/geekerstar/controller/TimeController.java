package com.geekerstar.controller;

import com.geekerstar.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * date: 2019-08-17 09:16
 * description:
 */
@RestController
public class TimeController {

    @PostMapping("/time")
    public String createTime(User user){
        System.out.println(user.getCreateTime().toString());
        return "success";
    }
}
