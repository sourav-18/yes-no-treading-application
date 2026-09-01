package com.ms.yes_no_treading_application.exceptions;

import com.ms.yes_no_treading_application.dtos.ApiErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, List<String>> errorsMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                error -> Objects.requireNonNullElse(
                                        error.getDefaultMessage(),
                                        "Invalid value"
                                ),
                                Collectors.toList()
                        )
                ));
        Map.Entry<String,List<String>> entry=errorsMessages.entrySet().iterator().next();
        String errorsMessage=entry.getKey()+" "+String.join(" and ",entry.getValue());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponseDto(HttpStatus.BAD_REQUEST.value(),errorsMessage));
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ApiErrorResponseDto> handleValidationException(UserAlreadyExist ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(new ApiErrorResponseDto(ex.getStatusCode(),ex.getMessage()));
    }

}
