package com.geekerstar.controller;

import com.geekerstar.pojo.Demo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

    /**
     * Spring Boot默认使用的json解析框架是jackson
     */
    @RequestMapping("/get")
    public Demo getDemo() {
        Demo demo = new Demo();
        demo.setId(1);
        demo.setName("Demo");
        demo.setCreateTime(new Date());
        demo.setRemarks("这是备注信息");
        return demo;
    }

}
