package com.example.projectfort1.exception;

import jakarta.validation.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Для базы данных
    @ExceptionHandler(DataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleDataException(final DataException e) {
        return new ApiError(HttpStatus.BAD_REQUEST, "Размер строки должен быть в диапазоне от 1 до 1000 символов");
    }

    // Для контроллера
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleConstraintViolationException(ConstraintViolationException e) {
        return new ApiError(HttpStatus.BAD_REQUEST, "Размер строки должен быть в диапазоне от 1 до 1000 символов");
    }
}
