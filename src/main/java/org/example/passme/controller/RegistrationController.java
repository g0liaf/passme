package org.example.passme.controller;

import org.example.passme.entity.User;
import org.example.passme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    public static final String REGISTRATION = "registration";
    public static final String REGISTRATION_MAPPING = "/registration";
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(REGISTRATION_MAPPING)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return REGISTRATION;
    }

    @PostMapping(REGISTRATION_MAPPING)
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return REGISTRATION;
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return REGISTRATION;
        }

        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return REGISTRATION;
        }

        return "redirect:/login";
    }

}
