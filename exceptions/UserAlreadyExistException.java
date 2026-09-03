package com.ms.yes_no_treading_application.exceptions;

import lombok.Getter;

@Getter
public class UserAlreadyExistException extends RuntimeException {
    private final String key;
    private final String value;
    private final int StatusCode;

    public UserAlreadyExistException(String key, String value) {
        super("User already exist with "+key+":"+value);
        this.key=key;
        this.value=value;
        this.StatusCode=400;
    }
}
