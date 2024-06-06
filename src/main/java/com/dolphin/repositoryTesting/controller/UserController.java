package com.dolphin.repositoryTesting.controller;

import com.dolphin.repositoryTesting.model.Role;
import com.dolphin.repositoryTesting.model.User;
import com.dolphin.repositoryTesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("roles", Role.values());
        return "user/createUser";
    }

    @PostMapping("/createUser")
    public String createUser(
            @RequestParam("username") String username,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("role") String role,
            Model model) {

        LocalDate dob = LocalDate.parse(dateOfBirth);
        User user = new User(username, dob, Role.valueOf(role));

        userService.addUser(user);

        model.addAttribute("user", user);

        return "user/userCreated";
    }

    @GetMapping("/viewUser/{id}")
    public String viewOne(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user/viewUser";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteOne(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/updateUser/{id}")
    public String updateOne(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", Role.values());
        return "user/updateUser";
    }

    @PostMapping("/updateUser")
    public String updatePerson(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
