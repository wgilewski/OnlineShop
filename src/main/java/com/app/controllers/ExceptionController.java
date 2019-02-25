package com.app.controllers;

import com.app.exceptions.MyException;
import com.app.exceptions.RegistrationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({RegistrationException.class})
    public String myExceptionHandler(RegistrationException e, Model model) {
        model.addAttribute("exceptionMessage", e.getMessage());
        model.addAttribute("exceptionLocalDate", e.getExceptionDateTime());
        return "errors/unLoggedExceptions";
    }

    @ExceptionHandler({MyException.class})
    public String myExceptionHandler(MyException e, Model model) {
        model.addAttribute("exceptionMessage", e.getMessage());
        model.addAttribute("exceptionLocalDate", e.getExceptionDateTime());
        return "errors/loggedExceptions";
    }

}
