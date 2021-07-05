package org.example.passme.controller;

import org.example.passme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    public static final String ADMIN = "admin";
    public static final String ADMIN_MAPPING = "/admin";

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ADMIN_MAPPING)
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return ADMIN;
    }

    @PostMapping(ADMIN)
    public String deleteUser(@RequestParam(defaultValue = "") Long userId,
                             @RequestParam(defaultValue = "") String action) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:" + ADMIN_MAPPING;
    }

    @GetMapping(ADMIN + "/{userId}")
    public String getUser(@PathVariable Long userId, Model model) {
        model.addAttribute("allUsers", userService.findUserById(userId));
        return ADMIN;
    }
}
