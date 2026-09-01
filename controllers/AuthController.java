package com.ms.yes_no_treading_application.controllers;


import com.ms.yes_no_treading_application.dtos.ApiResponseDto;
import com.ms.yes_no_treading_application.dtos.UserDto;
import com.ms.yes_no_treading_application.dtos.UserSignupRequestDto;
import com.ms.yes_no_treading_application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<UserDto>> signup(@Valid @RequestBody UserSignupRequestDto body){
        UserDto userDto=userService.signup(body);
        ApiResponseDto<UserDto> apiResponse =
                new ApiResponseDto<>(HttpStatus.CREATED.value(), "User signup successfully",userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
