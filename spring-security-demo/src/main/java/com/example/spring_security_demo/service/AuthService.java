package com.example.spring_security_demo.service;

import com.example.spring_security_demo.model.LoginResponse;
import com.example.spring_security_demo.security.JwtIssuer;
import com.example.spring_security_demo.security.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtIssuer jwtIssuer, AuthenticationManager authenticationManager) {
        this.jwtIssuer = jwtIssuer;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse attemptLogin(String email, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var principal =  (UserPrincipal)authentication.getPrincipal();
        var roles =  principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
        return new LoginResponse(token);
    }
}
