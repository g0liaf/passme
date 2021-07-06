package org.example.passme.controller;

import org.example.passme.entity.Login;
import org.example.passme.entity.User;
import org.example.passme.repository.VaultRepository;
import org.example.passme.service.LoginService;
import org.example.passme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class VaultController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private VaultRepository vaultRepository;

    @GetMapping("/vault")
    public String vault(Model model, Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        user = userService.findUserById(user.getId());
        var vault = user.getVault();
        model.addAttribute("vault", vault);
        model.addAttribute("loginForm", new Login());
        return "vault";
    }

    @PostMapping("/vault/{vaultId}/login")
    public String login(@PathVariable Long vaultId, @ModelAttribute("loginForm") @Valid Login loginForm,
                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "vault";
        }

        var vault = vaultRepository.getById(vaultId);
        loginForm.setVault(vault);
        try {
            loginService.saveLogin(loginForm);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "redirect:/vault";
    }
}
