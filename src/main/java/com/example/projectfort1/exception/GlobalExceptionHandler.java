package com.example.projectfort1.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Для базы данных
    @ExceptionHandler(DataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleDataException(final DataException e) {
        log.error("Произошла ошибка при добавлении объекта в базу данных", e);
        return new ApiError(HttpStatus.BAD_REQUEST, "Размер строки должен быть в диапазоне от 1 до 1000 символов");
    }

    // Для контроллера
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleConstraintViolationException(ConstraintViolationException e) {
        log.error("Произошла ошибка при обработке данных в контроллере", e);
        return new ApiError(HttpStatus.BAD_REQUEST, "Размер строки должен быть в диапазоне от 1 до 1000 символов");
    }
}
