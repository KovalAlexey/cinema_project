package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody UserResponseDto userResponseDto) {
        authService.register(userResponseDto.getEmail(), userResponseDto.getPassword());
    }
}
