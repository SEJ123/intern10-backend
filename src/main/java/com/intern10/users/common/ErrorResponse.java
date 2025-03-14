package com.intern10.users.common;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final ErrorDetails error;


    public ErrorResponse(String code, String message) {
        this.error = new ErrorDetails(code, message);
    }

    @Getter
    public static class ErrorDetails{
        private final String code;
        private final String message;

        public ErrorDetails(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}