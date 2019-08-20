package com.geekerstar.controller;

import com.geekerstar.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * date: 2019-08-20 14:36
 * description:
 */
@Controller
public class ThymeleafController {

    @RequestMapping("/")
    public String index(ModelMap map){
        map.addAttribute("message","https://www.geekerstar.com");
        return "hello";
    }

    @RequestMapping("/string")
    public String string(ModelMap map){
        map.addAttribute("userName","geekerstar");
        return "string";
    }

    @RequestMapping("/if")
    public String ifunless(ModelMap map) {
        map.addAttribute("flag", "yes");
        return "if";
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }

    @RequestMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("type", "link");
        map.addAttribute("pageId", "springcloud/2019/09/11/");
        map.addAttribute("img", "https://cdn.jikewenku.com/wp-content/uploads/2019/08/a3ae8b56c4db37cf9e438c63c410d1ee.png");
        return "url";
    }

    @RequestMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "neo");
        map.addAttribute("age", 30);
        map.addAttribute("flag", "yes");
        return "eq";
    }

    @RequestMapping("/switch")
    public String switchcase(ModelMap map) {
        map.addAttribute("sex", "woman");
        return "switch";
    }

    private List<User> getUserList(){
        List<User> list=new ArrayList<User>();
        User user1=new User("大牛",12,"123456");
        User user2=new User("小牛",6,"123563");
        User user3=new User("Geeker",66,"666666");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }
}
