package ru.aston.gamerent.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.aston.gamerent.model.exception.BadRequestException;
import ru.aston.gamerent.model.exception.CurrencyConvertingException;
import ru.aston.gamerent.model.exception.NoEntityException;

@ControllerAdvice
public class ExceptionsHandleController {

    @ExceptionHandler(NoEntityException.class)
    public ResponseEntity<String> handleNoEntityException(NoEntityException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }

    @ExceptionHandler(CurrencyConvertingException.class)
    public ResponseEntity<String> handleBadRequestException(CurrencyConvertingException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getLocalizedMessage());
    }
}
