package com.ms.yes_no_treading_application.exceptions;

import com.ms.yes_no_treading_application.entities.UserEntity;
import lombok.Getter;

@Getter
public class InsufficientBalanceException extends RuntimeException{
    private final double balance;
    private final UserEntity userEntity;
    private final int statusCode;

    public InsufficientBalanceException(double balance, UserEntity userEntity) {
        super("Insufficient balance");
        this.balance = balance;
        this.userEntity = userEntity;
        this.statusCode=400;
    }
}
