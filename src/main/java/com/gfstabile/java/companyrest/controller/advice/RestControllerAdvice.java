package com.gfstabile.java.companyrest.controller.advice;

import com.gfstabile.java.companyrest.entity.error.ValidationErrorCollection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorCollection> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
            .getAllErrors()
            .stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());
        return new ResponseEntity<>(new ValidationErrorCollection(errors), HttpStatus.BAD_REQUEST);
    }
}
