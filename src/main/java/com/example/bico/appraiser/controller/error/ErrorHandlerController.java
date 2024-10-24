package com.example.bico.appraiser.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandlerController {

    // Handle generic exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Bikings Error: " + ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
