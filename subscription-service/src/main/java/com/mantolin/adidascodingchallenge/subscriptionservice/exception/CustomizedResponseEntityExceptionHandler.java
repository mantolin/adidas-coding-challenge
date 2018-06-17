/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception Handler
 * 
 * @author Mikel Antolin Sola
 *
 */
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Any unspecified exception handler
     * 
     * @param ex
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * SubscriptionServiceException exception handler
     * 
     * @param ex
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(SubscriptionServiceException.class)
    public final ResponseEntity<Object> handleSubscriptionExceptions(SubscriptionServiceException ex,
            WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Service Failed", ex.getMessage());
        return new ResponseEntity<Object>(errorDetails, HttpStatus.CONFLICT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.method.annotation.
     * ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.
     * springframework.web.bind.MethodArgumentNotValidException,
     * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
     * org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
                ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
