package com.godwin.inventory.controller;

import com.godwin.inventory.models.Role;
import com.godwin.inventory.models.User;
import com.godwin.inventory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @PostMapping("/register")
    public User testRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam(required = false) Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);

        return userService.registerUser(user);
    }
}