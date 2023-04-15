package com.system.service;

import com.system.common.JwtService;
import com.system.controller.request.LoginRequest;
import com.system.controller.response.LoginResponse;
import com.system.entity.User;
import com.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        String token = jwtService.generateToken(user.getUsername());
        return new LoginResponse(token);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User find() {
        return userRepository.findByUsername("username");
    }

}

