package com.ms.yes_no_treading_application.dtos.validator;

import com.ms.yes_no_treading_application.dtos.anotations.ValidEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {
    private Set<String> acceptedValues;

    @Override
    public void initialize(ValidEnum annotation) {
        acceptedValues = Arrays.stream(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("enter");
        if (value == null) {
            return true; // Let @NotNull/@NotBlank handle null
        }

        if (acceptedValues.contains(value)) {
            return true;
        }
        String message = "Value must be one of: "
                + String.join(", ", acceptedValues);

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();

        return false;
    }
}
