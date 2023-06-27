package com.ahut.controller;

import com.ahut.pojo.Admin;
import com.ahut.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(String name, String password, Model model){
        Admin admin = adminService.login(name, password);
        if(admin != null){
            model.addAttribute("admin",admin);
            return "main";
        }else {
            model.addAttribute("errmsg","用户名或密码错误！");
            return "login";
        }
    }
}
