package com.example.spring_security_demo.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(authorities = "ROLE_ADMIN")
public @interface WithAdminUser {
}
