package com.marktsoft.practice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class NotFoundRequestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(NotFoundRequestException exception) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        NotFoundException notFoundException = new NotFoundException(
                exception.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Europe/Budapest")));

        return new ResponseEntity<>(notFoundException, badRequest);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        NotFoundException notFoundException = new NotFoundException(
                ex.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Europe/Budapest")));
        return new ResponseEntity<>(notFoundException, HttpStatus.BAD_REQUEST);
    }
}
