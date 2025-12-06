package com.godwin.inventory.controller;

import com.godwin.inventory.models.User;
import com.godwin.inventory.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user, BindingResult result, Model model) {

        System.out.println("=== POST /register received ===");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + (user.getPassword() != null ? "***" : "NULL"));  // Add this
        System.out.println("Role: " + user.getRole());  // Add this
        System.out.println("Has errors: " + result.hasErrors());
        System.out.println("Error count: " + result.getErrorCount());

        if (result.hasErrors()) {

            result.getAllErrors().forEach(error -> {
                System.out.println("  - " + error.getDefaultMessage());
            });

            return "register";
        }

        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }

        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        userService.registerUser(user);
        return "redirect:/login?registered";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
}
