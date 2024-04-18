package com.iKeeper.homepage.global.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResponseError {

    private ZonedDateTime timeStamp;
    private int resultCode;
    private String errorCode;
    private String message;

    public ResponseError(ErrorCode errorCode, String message) {

        this.timeStamp = ZonedDateTime.now();
        this.resultCode = errorCode.getResultCode();
        this.errorCode = errorCode.getCode();
        this.message = message;
    }
}
