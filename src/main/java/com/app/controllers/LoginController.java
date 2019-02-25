package com.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/mainPage")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/")
    public String index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            return "redirect:/admin";
        } else {
            return "redirect:/user";
        }
    }

    @GetMapping("/login")
    public String loginGet(Model model) {
        model.addAttribute("error", "");
        return "unLogged/loginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("error", "Niepoprawne dane logowania");
        return "unLogged/loginForm";
    }

}

