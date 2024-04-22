package com.iKeeper.homepage.global.httpStatus;

public class ResponseMessage {

    //CALENDAR
    public static final String CALENDAR_POST = "일정 등록 성공";
    public static final String CALENDAR_READ_DATE = "특정 일자 일정 조회 성공";
    public static final String CALENDAR_READ_ALL = "전체 일정 조회 성공";
    public static final String CALENDAR_PATCH = "일정 정보 수정 성공";
    public static final String CALENDAR_DELETE = "일정 삭제 성공";

    //AUTH
    public static final String AUTH_POST_JOIN = "회원 가입 성공";
    public static final String AUTH_POST_LOGIN = "로그인 성공";

    //USER
    public static final String USER_MYPAGE = "회원 정보 조회 성공";
    public static final String USER_UPDATE_MYPAGE = "회원 정보 수정 성공";
    public static final String USER_DELETE_ACCOUNT = "계정 탈퇴 성공";
}
