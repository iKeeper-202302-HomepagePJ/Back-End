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
    CALENDAR_NOT_FOUND(404, "NOT_FOUND"),

    //JWT
    JWT_AUTHORITY_NOT_FOUND(400, "BAD_REQUEST"),
    JWT_INVALID_TOKEN(400, "BAD_REQUEST"),
    JWT_EXPIRED_TOKEN(400, "BAD_REQUEST"),
    JWT_UNSUPPORTED_TOKEN(400, "BAD_REQUEST"),
    JWT_CLAIMS_STRING_EMPTY(400, "BAD_REQUEST"),

    //AUTH
    AUTH_INVALID_VALUE(400, "BAD_REQUEST"),
    AUTH_MEMBER_DUPLICATE(409, "CONFLICT"),
    AUTH_MEMBER_NOT_FOUND(404, "NOT_FOUND"),
    AUTH_MEMBER_LOGIN_FAIL(401, "UNAUTHORIZED"),

    //USER
    USER_MEMBER_NOT_FOUND(404, "NOT_FOUND"),
    USER_INVALID_VALUE(400, "BAD_REQUEST"),

    //POST
    POST_INVALID_VALUE(400, "BAD_REQUEST"),

    //BOOK
    BOOK_INVALID_VALUE(400, "BAD_REQUEST");

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
