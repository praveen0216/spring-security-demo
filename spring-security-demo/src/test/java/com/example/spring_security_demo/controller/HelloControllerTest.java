package com.example.spring_security_demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    void greeting() throws Exception {

        api.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("hello")));
    }

    @Test
    void securedShouldThrowEx() throws Exception {
        api.perform(get("/secured"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void securedShouldlogin() throws Exception {
        api.perform(get("/secured"))
                .andExpect(status().isOk());
    }

    @Test
    void admin_unauthorized() throws Exception {
        api.perform(get("/admin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void admin_unauthorizedAnyUser() throws Exception {
        api.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void admin_ok() throws Exception {
        api.perform(get("/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAdminUser
    void admin_ok_1() throws Exception {
        api.perform(get("/admin"))
                .andExpect(status().isOk());
    }
}