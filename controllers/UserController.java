package com.ms.yes_no_treading_application.controllers;

import com.ms.yes_no_treading_application.dtos.ApiResponseDto;
import com.ms.yes_no_treading_application.dtos.UserDto;
import com.ms.yes_no_treading_application.dtos.UserSignupRequestDto;
import com.ms.yes_no_treading_application.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

private final UserService userService;

    @PostMapping("/details")
    public ResponseEntity<ApiResponseDto<UserDto>> signup(){
        Long id=1l;
        UserDto userDto=userService.details(id);
        ApiResponseDto<UserDto> apiResponse =
                new ApiResponseDto<>(HttpStatus.OK.value(), "User details fetch successfully",userDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
