package com.app.exceptions;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class MyException extends RuntimeException {
    private String exceptionMessage;
    private LocalDateTime exceptionDateTime;

    @Override
    public String getMessage() {
        return exceptionMessage;
    }

    public LocalDateTime getExceptionDateTime() {
        return exceptionDateTime;
    }
}
