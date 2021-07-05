package org.example.passme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VaultController {
    @GetMapping("/vault")
    public String cabinet(Model model) {
        return "vault";
    }
}
