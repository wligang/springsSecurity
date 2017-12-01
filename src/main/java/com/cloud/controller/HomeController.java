package com.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloud.pojo.Msg;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","haha，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }
}
