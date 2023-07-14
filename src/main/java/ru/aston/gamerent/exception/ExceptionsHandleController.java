package ru.aston.gamerent.exception;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandleController {
    public static final String SERVER_ERROR_PAGE = "500";
    public static final String LOGIN_PAGE = "login";
    public static final String NOT_FOUND_PAGE = "404";
    public static final String BAD_REQUEST_PAGE = "400";

    @ExceptionHandler(NoEntityException.class)
    public String handleNoEntityException(NoEntityException e) {
        return NOT_FOUND_PAGE;
    }

    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(BadRequestException e) {
        return BAD_REQUEST_PAGE;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String handleUserNotFoundException(BadCredentialsException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return LOGIN_PAGE;
    }

    @ExceptionHandler(CurrencyConvertingException.class)
    public String handleBadRequestException() {
        return SERVER_ERROR_PAGE;
    }

    @ExceptionHandler(Exception.class)
    public String handleException() {
        return SERVER_ERROR_PAGE;
    }
}
