package com.ms.yes_no_treading_application.Mapper;

import com.ms.yes_no_treading_application.dtos.UserSignupRequestDto;
import com.ms.yes_no_treading_application.entities.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(UserSignupRequestDto userSignupRequestDto){
       return UserEntity.builder().name(userSignupRequestDto.getName())
                .email(userSignupRequestDto.getEmail())
                .password(userSignupRequestDto.getPassword())
                .build();
    }
}
