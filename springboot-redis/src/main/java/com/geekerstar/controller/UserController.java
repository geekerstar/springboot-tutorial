package com.geekerstar.controller;

import com.geekerstar.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author geekerstar
 * date: 2019-08-20 11:54
 * description:
 */
@RestController
public class UserController {

    @RequestMapping("/getUser")
    @Cacheable("user-key")
    public User getUser() {
        User user = new User("geeker@qq.com", "aa", "12312312", "aaa", "122'");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/uuid")
    public String uid(HttpSession session) {
        UUID uuid = (UUID) session.getAttribute("uuid");
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        session.setAttribute("uuid", uuid);
        return session.getId();
    }
}
