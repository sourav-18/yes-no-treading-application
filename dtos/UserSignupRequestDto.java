package com.ms.yes_no_treading_application.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;


@Getter
@Setter
@NoArgsConstructor
public class UserSignupRequestDto {

    @Size(max = 100,min = 2)
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Size(max = 100,min = 2)
    @NotBlank
    private String password;
}
