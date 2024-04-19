package com.iKeeper.homepage.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode implements ErrorModel {

    //CALENDAR
    CALENDAR_INVALID_VALUE(400, "BAD_REQUEST"),
    CALENDAR_NOT_FOUND(400, "NOT_FOUND"),
    //AUTH
    AUTH_INVALID_VALUE(400, "BAD_REQUEST"),
    AUTH_MEMBER_DUPLICATE(400, "CONFLICT"),
    AUTH_MEMBER_NOT_FOUND(400, "NOT_FOUND");

    private int resultCode;
    private String code;

    ErrorCode(int resultCode, String code) {
        this.resultCode = resultCode;
        this.code = code;
    }

    @Override
    public int getResultCode() {
        return resultCode;
    }

    @Override
    public String getCode() {
        return code;
    }
}
