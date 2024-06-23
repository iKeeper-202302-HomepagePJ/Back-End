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
    public static final String USER_MAJOR_LIST = "전공 목록 조회 성공";
    public static final String USER_MYPAGE_PATCH = "회원 정보 수정 성공";
    public static final String USER_MYPAGE_DELETE = "계정 탈퇴 성공";
    public static final String USER_MYPAGE_POST = "마이페이지 게시글 조회 성공";
    public static final String USER_MYPAGE_COMMENT = "마이페이지 댓글 조회 성공";

    //POST
    public static final String POST_POST = "게시글 생성 성공";
    public static final String POST_POST_CATEGORYLARGE = "게시글 대분류 카테고리 생성 성공";
    public static final String POST_POST_CATEGORYSMALL = "게시글 소분류 카테고리 생성 성공";
    public static final String POST_POST_HEADLINE = "게시글 머릿말 생성 성공";
    public static final String POST_POST_COMMENT = "게시글 댓글 생성 성공";
    public static final String POST_POST_BOOKMARK = "게시글 북마크 생성 성공";
    public static final String POST_READ = "게시글 조회 성공";
    public static final String POST_READ_ALL = "게시글 전체 조회 성공";
    public static final String POST_READ_CATEGORYLARGE = "게시글 대분류 카테고리 조회 성공";
    public static final String POST_READ_HEADLINE = "게시글 머릿말 조회 성공";
    public static final String POST_READ_BOOKMARK = "게시글 북마크 조회 성공";
    public static final String POST_PATCH = "게시글 수정 성공";
    public static final String POST_DELETE = "게시글 삭제 성공";
    public static final String POST_DELETE_CATEGORYLARGE = "게시글 대분류 카테고리 삭제 성공";
    public static final String POST_DELETE_CATEGORYSMALL = "게시글 소분류 카테고리 삭제 성공";
    public static final String POST_DELETE_HEADLINE = "게시글 머릿말 삭제 성공";
    public static final String POST_DELETE_BOOKMARK = "게시글 북마크 삭제 성공";
    public static final String POST_DELETE_COMMENT = "게시글 댓글 삭제 완료";

    //BOOK
    public static final String BOOK_POST = "도서 등록 성공";
    public static final String BOOK_READ_ALL = "도서 전체 조회 성공";
}
