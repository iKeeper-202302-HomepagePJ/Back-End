package com.iKeeper.homepage.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ResponseError> customException(CustomException e) {
        ResponseError responseError = new ResponseError(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ResponseError> loginFailException() {
        ResponseError responseError = new ResponseError(ErrorCode.AUTH_MEMBER_LOGIN_FAIL,
                "아이디와 패스워드가 일치하지 않습니다.");
        return new ResponseEntity<>(responseError, HttpStatus.UNAUTHORIZED);
    }
}