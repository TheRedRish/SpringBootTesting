package com.dolphin.repositoryTesting.controller;

import com.dolphin.repositoryTesting.model.User;
import com.dolphin.repositoryTesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        List<User> personList = userService.fetchAll();
        model.addAttribute("users", personList);
        return "home/index";
    }
}
