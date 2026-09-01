package com.ms.yes_no_treading_application.entities.types;

public enum OptionType {
    YES("yes"),
    NO("no");

    private final String value;
    OptionType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
