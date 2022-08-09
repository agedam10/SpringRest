package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException ex) {
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();
        customerErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        customerErrorResponse.setMessage(ex.getMessage());
        customerErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(customerErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleGenericException(Exception ex) {
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();
        customerErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customerErrorResponse.setMessage(ex.getMessage());
        customerErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(customerErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
