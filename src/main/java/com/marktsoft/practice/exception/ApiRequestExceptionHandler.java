package com.marktsoft.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiRequestExceptionHandler {

    public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception) {

        ApiException apiException = new ApiException(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Europe/Paris")));

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
