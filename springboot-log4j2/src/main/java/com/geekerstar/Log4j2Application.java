package com.geekerstar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author geekerstar
 * date: 2019-08-16 15:52
 * description:
 */
@SpringBootApplication
@RestController
public class Log4j2Application {
    public static void main(String[] args) {
        SpringApplication.run(Log4j2Application.class,args);
    }

    private static final Logger logger = LogManager.getLogger(Log4j2Application.class);

    @GetMapping("/test")
    public String test(){
        logger.debug("debug级别的日志……");
        logger.info("info级别的日志……");
        logger.warn("warn级别的日志……");
        logger.error("error级别日志……");
        logger.fatal("fatal级别日志……");
        return "Log4j2日志框架";
    }
}
