package com.intern10.users.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getErrorCode().name(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, ex.getErrorCode().getStatus());
    }
}
