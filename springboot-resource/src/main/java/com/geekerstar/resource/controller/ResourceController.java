package com.geekerstar.resource.controller;

import com.geekerstar.resource.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * date: 2019/12/22 18:35
 * description:
 */
@RestController
public class ResourceController {
    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
    public Object hello() {
        return "hello springboot~~";
    }

    @RequestMapping("/getResource")
    public Object getResource() {
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource, bean);
        return bean;
    }
}
