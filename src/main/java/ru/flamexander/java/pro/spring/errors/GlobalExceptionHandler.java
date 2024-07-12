package ru.flamexander.java.pro.spring.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> catchResourceNotFoundException(ResourceNotFoundException e) {
        ErrorDto errorDto = new ErrorDto("RESOURCE_NOT_FOUND", e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppLogicException.class)
    public ResponseEntity<ErrorDto> catchResourceNotFoundException(AppLogicException e) {
        ErrorDto errorDto = new ErrorDto(e.getCode(), e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}