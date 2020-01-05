package com.geekerstar.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ToWebsocket {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/websocket")
    public String webSocket(@RequestParam(value="name")  String name, Model model) {
        try {
            logger.info("跳转到websocket的页面上");
            model.addAttribute("username", name);
            return "websocket";
        } catch (Exception e) {
            logger.info("跳转到websocket的页面上发生异常，异常信息是：" + e.getMessage());
            return "error";
        }
    }

}
