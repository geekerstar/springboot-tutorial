package com.geekerstar.aop.limit.controller;

import com.geekerstar.aop.limit.annotation.LocalLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2020/3/30 16:13
 * @description
 */
@RestController
@RequestMapping("/aop")
public class BookController {

    @LocalLock(key = "aop:arg[0]")
    @GetMapping("limit")
    public String query(@RequestParam String token) {
        return "ok- " + token;
    }
}
