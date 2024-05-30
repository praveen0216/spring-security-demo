package com.example.spring_security_demo.controller;


import com.example.spring_security_demo.model.LoginRequest;
import com.example.spring_security_demo.model.LoginResponse;
import com.example.spring_security_demo.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
       return authService.attemptLogin(request.getEmail(), request.getPassword());
    }
}
