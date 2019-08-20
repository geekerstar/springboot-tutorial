package com.geekerstar.aop.controller;

import com.geekerstar.aop.aspect.Weblog;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author geekerstar
 * date: 2019-08-16 16:59
 * description:
 */
@RestController
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * post 方式测试
     *
     * @param user
     * @return
     */
    @PostMapping("/user/login")
    @Weblog(description = "请求了用户登录接口")
    public User userLogin(@RequestBody User user) {
        logger.info("user login ...");
        return user;
    }

    /**
     * GET 方式测试
     *
     * @param userId
     * @return
     */
    @GetMapping("/user/{id}")
    @Weblog(description = "请求了用户登录接口")
    public String findUserInfo(@PathVariable("id") String userId) {
        logger.info("find user info ...");
        return "success";
    }

    @GetMapping("/test11")
    public String test() {
        logger.info("testget...");
        return "success";
    }

    /**
     * 单文件上传接口测试
     *
     * @return
     */
    @PostMapping("/file/upload")
    public String testFileUpload(@RequestParam("file") MultipartFile file) {
        logger.info("testFileUpload ...");
        return "success";
    }

    /**
     * 多文件上传接口测试
     *
     * @return
     */
    @PostMapping("/multiFile/upload")
    public String testMultiFileUpload(@RequestParam("file") MultipartFile[] file) {
        logger.info("testMultiFileUpload ...");
        return "success";
    }
}
