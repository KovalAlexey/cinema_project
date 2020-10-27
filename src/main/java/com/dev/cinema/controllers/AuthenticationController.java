package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.user.UserRequestDto;
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
    public void registration(@RequestBody UserRequestDto userRequestDto) {
        authService.register(userRequestDto.getEmail(), userRequestDto.getPassword());
    }
}
