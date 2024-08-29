package com.example.herbhub.controller;

import com.example.herbhub.dto.AuthenticationResponseDto;
import com.example.herbhub.dto.LoginDto;
import com.example.herbhub.dto.UserDto;
import com.example.herbhub.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody UserDto userDto) {
        authenticationService.register(userDto);
    }

    @PostMapping("/login")
    public AuthenticationResponseDto authenticate(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }

}
