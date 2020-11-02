package com.geekerstar.freemarker.controller;

import com.geekerstar.freemarker.method.SortMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author geekerstar
 * date: 2019-08-19 10:40
 * description:
 */
@Controller
@RequestMapping("/")
public class FreemarkerController {

    @GetMapping(value = "/test1")
    public ModelAndView test1() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username", "你好！Freemarker！");
        return modelAndView;
    }

    /**
     * freemarker取值，插值
     *
     * @return
     */
    @RequestMapping("/test2")
    public ModelAndView test2() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("intVar", 100);
        mv.addObject("LongVar", 100000000000L);
        mv.addObject("doubleVar", 3.1415926d);
        mv.addObject("stringVar", "字符串");
        mv.addObject("booleanVar", true);
        mv.addObject("dateVar", new Date());
        mv.addObject("nullVar1", null);
        mv.addObject("nullVar", "我是空");
        return mv;
    }


    @RequestMapping("/test3")
    public ModelAndView test3() {
        ModelAndView mv = new ModelAndView();
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("javascript");
        list.add("python");
        list.add("php");
        list.add("html");
        mv.addObject("mylist", list);

        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName(i + "name");
            student.setSex(i + "sex");
            studentList.add(student);
        }
        mv.addObject("student", studentList);
        return mv;
    }

    @RequestMapping(value = "/test4")
    public ModelAndView test4() {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        map.put("java", "你好");
        map.put("address", "北京");
        map.put("身高", 172);
        map.put("money", 123123);
        mv.addObject("map", map);
        return mv;
    }


    @RequestMapping("/test5")
    public ModelAndView test5() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("sort_int", new SortMethod());
        return mv;
    }

    /**
     * 本例子不成功，报错，原因是我没有配置自定义指令的xml文件。
     *
     * @return
     */
    @RequestMapping("/test6")
    public ModelAndView test6() {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

}
