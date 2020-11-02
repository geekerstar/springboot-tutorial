package com.geekerstar.webssh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author geekerstar
 * @date 2020/3/18 10:33
 * @description
 */
@Controller
public class WebsshController {
    @RequestMapping("/websshpage")
    public String websshpage() {
        return "webssh";
    }
}
