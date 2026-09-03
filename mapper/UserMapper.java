package com.ms.yes_no_treading_application.mapper;

import com.ms.yes_no_treading_application.dtos.UserDto;
import com.ms.yes_no_treading_application.dtos.UserSignupRequestDto;
import com.ms.yes_no_treading_application.entities.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(UserSignupRequestDto userSignupRequestDto){
       return UserEntity.builder().name(userSignupRequestDto.getName())
                .email(userSignupRequestDto.getEmail())
                .password(userSignupRequestDto.getPassword())
               .depositBalance(0.0)
               .winBalance(0.0)
                .build();
    }

    public static UserDto toDto(UserEntity user){
        return UserDto.builder().id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .depositBalance(user.getDepositBalance())
                .winBalance(user.getWinBalance())
                .build();
    }
}
