package com.system.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.common.JsonUntil;
import com.system.common.JwtService;
import com.system.common.UserContext;
import com.system.controller.response.ResponseData;
import com.system.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        UserContext.removeUser();

        HttpServletRequest req = (HttpServletRequest) request;
        String requestUrl = req.getRequestURI();

        if (Objects.equals(requestUrl, "/api/login")
                && StringUtils.endsWithIgnoreCase(req.getMethod(), "post")) {
            chain.doFilter(request, response);
            return;
        }

        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            genNoAuthResponse((HttpServletResponse) response);
            return;
        }

        String token = header.replace("Bearer ", "");

        try {
            Claims claims = jwtService.parseToken(token);
            UserContext.setUser(userRepository.findByUsername(claims.getSubject()));
        } catch (Exception ex) {
            genNoAuthResponse((HttpServletResponse) response);
            return;
        }
        chain.doFilter(request, response);
    }

    private void genNoAuthResponse(HttpServletResponse response) {
        HttpServletResponse httpResponse = response;
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer;
        try {
            writer = httpResponse.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.write(JsonUntil.parseObject(ResponseData.noAuth()));
        writer.flush();
        writer.close();
    }
}

