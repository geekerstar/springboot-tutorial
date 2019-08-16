package com.geekerstar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * date: 2019-08-16 15:45
 * description:
 */
@RestController
@Slf4j
public class WaitController {
    @GetMapping("/wait")
    String hello() throws InterruptedException {
        log.info("=============== start ===============");
        //模拟一个50ms的业务处理时间
        TimeUnit.MILLISECONDS.sleep(50);
        log.info("=============== end =================");
        return "hello springboot!";
    }
}
