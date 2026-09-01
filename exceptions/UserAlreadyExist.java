package com.ms.yes_no_treading_application.exceptions;

import lombok.Getter;

@Getter
public class UserAlreadyExist extends RuntimeException {
    private String key;
    private String value;
    private int StatusCode;

    public UserAlreadyExist(String key,String value) {
        super("User already exist with "+key+":"+value);
        this.key=key;
        this.value=value;
        this.StatusCode=400;
    }
}
