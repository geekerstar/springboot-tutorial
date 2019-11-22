package com.geekerstar.controller;

import com.geekerstar.entity.User2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 案例二
 */
@Controller
@RequestMapping("two")
public class TwoController {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "geek");
        return "thymeleaf/index";
    }

    @RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }

    @RequestMapping("test")
    public String test(ModelMap map) {

        User2 user = new User2();
        user.setAge(18);
        user.setName("manager");
        user.setPassword("123456");
        user.setBirthday(new Date());

        map.addAttribute("user", user);


        User2 u1 = new User2();
        u1.setAge(19);
        u1.setName("geek");
        u1.setPassword("123456");
        u1.setBirthday(new Date());

        User2 u2 = new User2();
        u2.setAge(17);
        u2.setName("LeeCX");
        u2.setPassword("123456");
        u2.setBirthday(new Date());

        List<User2> userList = new ArrayList<>();
        userList.add(user);
        userList.add(u1);
        userList.add(u2);

        map.addAttribute("userList", userList);

        return "thymeleaf/test";
    }

    @PostMapping("postform")
    public String postform(User2 user) {
        System.out.println(user.getName());
        return "redirect:/th/test";
    }

}
