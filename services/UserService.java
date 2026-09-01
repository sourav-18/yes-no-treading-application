package com.ms.yes_no_treading_application.services;

import com.ms.yes_no_treading_application.Mapper.UserMapper;
import com.ms.yes_no_treading_application.dtos.UserDto;
import com.ms.yes_no_treading_application.dtos.UserSignupRequestDto;
import com.ms.yes_no_treading_application.entities.UserEntity;
import com.ms.yes_no_treading_application.exceptions.UserAlreadyExist;
import com.ms.yes_no_treading_application.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto signup(UserSignupRequestDto userSignupRequestDto) {
        UserEntity isUserExist = userRepository.findByEmail(userSignupRequestDto.getEmail());
        if (isUserExist != null) {
            throw new UserAlreadyExist("email", userSignupRequestDto.getEmail());
        }
        UserEntity newUser = userRepository.save(UserMapper.toEntity(userSignupRequestDto));
        return UserMapper.toDto(newUser);
    }
}
