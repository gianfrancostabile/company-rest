package com.gfstabile.java.companyrest.controller.advice;

import com.gfstabile.java.companyrest.entity.error.ValidationErrorCollection;
import com.gfstabile.java.companyrest.exception.AbstractServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The main controller advice for unhandled exceptions
 *
 * @author G. F. STabile
 */
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

    @ExceptionHandler(AbstractServiceException.class)
    public ResponseEntity<Void> handleAbstractServiceException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ValidationErrorCollection> handleHttpMessageNotReadableException() {
        List<String> errors = new ArrayList<>();
        errors.add("The body is mandatory or the body structure is not tolerated");
        return new ResponseEntity<>(new ValidationErrorCollection(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ValidationErrorCollection> handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException exception) {
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        return new ResponseEntity<>(new ValidationErrorCollection(errors), HttpStatus.BAD_REQUEST);
    }
}
