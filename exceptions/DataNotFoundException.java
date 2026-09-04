package com.ms.yes_no_treading_application.exceptions;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {
    private final String key;
    private final int statusCode;

    public DataNotFoundException(String key) {
        super(key+" not found");
        this.key = key;
        this.statusCode=400;
    }
}
