package com.system.service;

import com.system.common.JwtUtil;
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
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token);
    }
}

