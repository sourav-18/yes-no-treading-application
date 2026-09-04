package com.ms.yes_no_treading_application.services;

import com.ms.yes_no_treading_application.exceptions.DataNotFoundException;
import com.ms.yes_no_treading_application.mapper.UserMapper;
import com.ms.yes_no_treading_application.dtos.UserDto;
import com.ms.yes_no_treading_application.dtos.UserSignupRequestDto;
import com.ms.yes_no_treading_application.entities.UserEntity;
import com.ms.yes_no_treading_application.exceptions.UserAlreadyExistException;
import com.ms.yes_no_treading_application.Repository.UserRepository;
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
            throw new UserAlreadyExistException("email", userSignupRequestDto.getEmail());
        }
        UserEntity newUser = userRepository.save(UserMapper.toEntity(userSignupRequestDto));
        return UserMapper.toDto(newUser);
    }

    public UserDto details(Long id){
        UserEntity user=userRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException("User"));
        return UserMapper.toDto(user);
    }
}
