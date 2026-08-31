package com.ms.yes_no_treading_application.controllers;


import com.ms.yes_no_treading_application.dtos.UserSignupRequestDto;
import com.ms.yes_no_treading_application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody UserSignupRequestDto body){
        userService.signup(body);
    }
}
