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
    CALENDAR_NOT_FOUND(400, "BAD_REQUEST");

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
