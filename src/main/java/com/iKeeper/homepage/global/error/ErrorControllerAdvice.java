package com.iKeeper.homepage.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ResponseError> customException(CustomException e) {
        ResponseError responseError = new ResponseError(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }
}
