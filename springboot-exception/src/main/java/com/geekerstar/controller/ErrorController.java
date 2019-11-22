package com.geekerstar.controller;

import com.geekerstar.pojo.WebJsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController {

    @RequestMapping("test")
    public String error() {
        int a = 1 / 0;
        return "error";
    }

    @RequestMapping("/ajaxerror")
    public String ajaxError() {
        return "ajaxerror";
    }

    @RequestMapping("/getAjaxerror")
    @ResponseBody
    public WebJsonResult getAjaxError() {
        int a = 1 / 0;
        return WebJsonResult.ok();
    }

}
