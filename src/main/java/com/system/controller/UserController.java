package com.system.controller;

import com.system.entity.User;
import com.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public User find() {
        return userService.find();
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }
}
