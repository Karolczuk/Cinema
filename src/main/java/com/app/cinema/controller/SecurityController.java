package com.app.cinema.controller;

import com.app.cinema.dto.UserDto;
import com.app.cinema.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping("/register")
    public com.app.dto.Info<String> register(@RequestBody UserDto registrationUser) {
        return com.app.dto.Info.<String>builder()
                .data(securityService.registerUser(registrationUser))
                .build();
    }
}
