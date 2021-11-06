package com.tryIt.advice;


import com.tryIt.entity.ErrorResponse;
import com.tryIt.exception.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.tryIt.entity.ErrorCode.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice("TryIt.com.tryIt.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidExceptionHandler(BindException e) {
        ErrorResponse response = ErrorResponse.of(ARGUMENT_INPUT_INVALID, e.getBindingResult());
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse response = ErrorResponse.of(INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> productNotExistException(ProductNotExistException e) {
        final ErrorResponse response = ErrorResponse.of(PRODUCT_NOTEXIST_ERROR);
        return new ResponseEntity<>(response, BAD_REQUEST);

    }


}
