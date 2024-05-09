package com.iKeeper.homepage.global.error;

public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomException(String s, ErrorCode errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return  errorCode;
    }
}
