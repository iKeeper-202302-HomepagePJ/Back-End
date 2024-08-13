package com.iKeeper.homepage.global.httpStatus;

public class ResponseMessage {

    //ATTENDANCE
    public static final String ATTENDANCE_LECTURE_LIST = "강의 목록 조회 성공";
    public static final String ATTENDANCE_LECTURE = "강의 조회 성공";
    public static final String ATTENDANCE_USER = "유저 출석 조회 성공";
    public static final String ATTENDANCE_POST = "출석 등록 성공";
    public static final String ATTENDANCE_PATCH = "출석 수정 성공";
    public static final String ATTENDANCE_DELETE = "출석 초기화 성공";
    public static final String ATTENDANCE_LECTURE_POST = "강의 생성 성공";
    public static final String ATTENDANCE_LECTURE_DELETE = "강의 삭제 성공";

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
    public static final String USER_MAJOR_LIST = "전공 목록 조회 성공";
    public static final String USER_MYPAGE_PATCH = "회원 정보 수정 성공";
    public static final String USER_MYPAGE_DELETE = "계정 탈퇴 성공";
    public static final String USER_MYPAGE_POST = "마이페이지 게시글 조회 성공";

    //POST
    public static final String POST_POST = "게시글 생성 성공";
    public static final String POST_POST_CATEGORY = "게시글 소분류 카테고리 생성 성공";
    public static final String POST_POST_HEADLINE = "게시글 머릿말 생성 성공";
    public static final String POST_POST_COMMENT = "게시글 댓글 생성 성공";
    public static final String POST_POST_BOOKMARK = "게시글 북마크 생성 성공";
    public static final String POST_READ = "게시글 조회 성공";
    public static final String POST_READ_ALL = "게시글 전체 조회 성공";
    public static final String POST_CATEGORY_LIST = "카테고리 별 게시글 조회 성공";
    public static final String POST_READ_CATEGORY = "게시글 대분류 카테고리 조회 성공";
    public static final String POST_READ_HEADLINE = "게시글 머릿말 조회 성공";
    public static final String POST_READ_BOOKMARK = "게시글 북마크 조회 성공";
    public static final String POST_PATCH = "게시글 수정 성공";
    public static final String POST_DELETE = "게시글 삭제 성공";
    public static final String POST_DELETE_CATEGORY = "게시글 소분류 카테고리 삭제 성공";
    public static final String POST_DELETE_HEADLINE = "게시글 머릿말 삭제 성공";
    public static final String POST_DELETE_BOOKMARK = "게시글 북마크 삭제 성공";
    public static final String POST_DELETE_COMMENT = "게시글 댓글 삭제 완료";
    public static final String POST_FILE_READ = "파일 목록 조회 성공";

    //BOOK
    public static final String BOOK_READ_ALL = "도서 전체 조회 성공";
    public static final String BOOK_POST = "도서 등록 성공";
    public static final String BOOK_DELETE = "도서 삭제 성공";
    public static final String BOOK_RENTAL_PATCH = "도서 대여 수정 성공";

    //INTRODUCE
    public static final String INTRODUCE_READ = "소개 조회 성공";
    public static final String INTRODUCE_PATCH = "소개 수정 성공";
    public static final String INTRODUCE_HYPERLINK_READ = "하이퍼링크 조회 성공";
    public static final String INTRODUCE_HYPERLINK_POST = "하이퍼링크 생성 성공";
    public static final String INTRODUCE_HYPERLINK_PATCH = "하이퍼링크 수정 성공";
    public static final String INTRODUCE_HISTORY_READ = "연혁 조회 성공";
    public static final String INTRODUCE_HISTORY_POST = "연혁 생성 성공";
    public static final String INTRODUCE_HISTORY_PATCH = "연혁 수정 성공";
    public static final String INTRODUCE_AWARD_READ = "수상 조회 성공";
    public static final String INTRODUCE_AWARD_POST = "수상 생성 성공";
    public static final String INTRODUCE_AWARD_PATCH = "수상 수정 성공";

    //LEDGER
    public static final String LEDGER_LIST_READ = "회비 조회 성공";
    public static final String LEDGER_POST = "회비 생성 성공";
    public static final String LEDGER_DELETE = "회비 삭제 성공";
}
