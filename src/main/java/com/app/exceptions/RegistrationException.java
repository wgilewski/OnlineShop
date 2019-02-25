package com.app.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class RegistrationException extends RuntimeException {

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
