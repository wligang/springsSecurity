package com.cloud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.pojo.User;
import com.cloud.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll() {
        List<User> list = userService.findAll();
        return list;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "html/index";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User(name, password);
        userService.save(user);
        return "success";
    }

}
