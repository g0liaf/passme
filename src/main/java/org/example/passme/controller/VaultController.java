package org.example.passme.controller;

import org.example.passme.entity.Login;
import org.example.passme.entity.User;
import org.example.passme.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class VaultController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/vault")
    public String vault(Model model, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        model.addAttribute("vault", user.getVault());
        return "vault";
    }

    @PostMapping("/vault/login")
    public String login(@ModelAttribute @Valid Login loginForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "vault";
        }

        try {
            loginService.saveLogin(loginForm);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "vault";
    }
}
