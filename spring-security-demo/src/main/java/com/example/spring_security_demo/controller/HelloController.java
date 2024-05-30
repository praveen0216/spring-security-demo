package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String greeting() {
        return "hello world ";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        return "if you see this then you. are logged in as user " + principal.getEmail()
                + " userID " + principal.getUserId();
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {
        return "if you see this then you. are logged in as admin  " + principal.getEmail()
                + " userID " + principal.getUserId();
    }
}
