package com.iKeeper.homepage.domain.user.controller;

import com.iKeeper.homepage.domain.user.dto.request.MemberRequest;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.domain.user.service.MajorService;
import com.iKeeper.homepage.domain.user.service.UserService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import com.iKeeper.homepage.global.jwt.handler.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final MajorService majorService;
    private final UserService userService;

    @GetMapping(value = "/major")
    public ResponseEntity majorList() {
        return ResponseEntity.ok(majorService.searchAllMajor());
    }

    @GetMapping(value = "/mypage")
    public ResponseEntity memberInfo(@RequestHeader("Authorization") String accessToken) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MYPAGE, userService.searchMemberInfo(studentId)), HttpStatus.OK);
    }

    @GetMapping(value = "/mypage/mypost")
    public ResponseEntity searchMyPost(@RequestHeader("Authorization") String accessToken) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MYPAGE, userService.searchMyPost(studentId)), HttpStatus.OK);
    }

    @GetMapping(value = "/mypage/mycomment")
    public ResponseEntity searchMyComment(@RequestHeader("Authorization") String accessToken) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MYPAGE, userService.searchMyComment(studentId)), HttpStatus.OK);
    }

    @PatchMapping(value = "/mypage")
    public ResponseEntity updateMemberInfo(@RequestHeader("Authorization") String accessToken,
                                           @RequestBody @Valid MemberRequest memberRequest,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("일부 입력된 값이 올바르지 않습니다.", ErrorCode.USER_INVALID_VALUE);
        }

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();

        userService.updateMemberInfo(studentId, memberRequest);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_UPDATE_MYPAGE, userService.updateMemberInfo(studentId, memberRequest)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/mypage")
    public ResponseEntity deleteAccount(@RequestHeader("Authorization") String accessToken) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_DELETE_ACCOUNT, userService.deleteAccount(studentId)), HttpStatus.OK);
    }
}