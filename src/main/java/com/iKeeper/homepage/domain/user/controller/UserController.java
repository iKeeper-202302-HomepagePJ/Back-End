package com.iKeeper.homepage.domain.user.controller;

import com.iKeeper.homepage.domain.post.dto.response.PostListResponse;
import com.iKeeper.homepage.domain.user.dto.request.MemberRequest;
import com.iKeeper.homepage.domain.user.dto.response.MemberInfoResponse;
import com.iKeeper.homepage.domain.user.dto.response.MemberListResponse;
import com.iKeeper.homepage.domain.user.service.UserService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import com.iKeeper.homepage.global.jwt.handler.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/members")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @GetMapping(value = "/field")
    public ResponseEntity fieldList() {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MAJOR_LIST, userService.searchAllField()), HttpStatus.OK);
    }

    @GetMapping(value = "/status")
    public ResponseEntity statusList() {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MAJOR_LIST, userService.searchAllStatus()), HttpStatus.OK);
    }

    @GetMapping(value = "/grade")
    public ResponseEntity gradeList() {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MAJOR_LIST, userService.searchAllGrade()), HttpStatus.OK);
    }

    @GetMapping(value = "/major")
    public ResponseEntity majorList() {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MAJOR_LIST, userService.searchAllMajor()), HttpStatus.OK);
    }

    @GetMapping(value = "/summary")
    public ResponseEntity summaryMember(@RequestHeader("Authorization") String accessToken) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
        Optional<MemberInfoResponse> memberInfo = this.userService.summaryMember(studentId);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MYPAGE, memberInfo), HttpStatus.OK);
    }

    @GetMapping(value = "/mypage")
    public ResponseEntity searchMember(@RequestHeader("Authorization") String accessToken) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
        Optional<MemberListResponse> memberList = this.userService.searchMember(studentId);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MYPAGE, memberList), HttpStatus.OK);
    }

    @GetMapping(value = "/post/{studentId}")
    public ResponseEntity getMyPostList(@PathVariable String studentId, @RequestParam(value = "page") int page) {

        Page<PostListResponse> paging = this.userService.getMyPostList(studentId, page);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MYPAGE_POST, paging), HttpStatus.OK);
    }

    /* @GetMapping(value = "/mypage/comment")
    public ResponseEntity searchMyComment(@RequestHeader("Authorization") String accessToken) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MYPAGE_COMMENT, userService.searchMyComment(studentId)), HttpStatus.OK);
    } */

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
                ResponseMessage.USER_MYPAGE_PATCH, userService.updateMemberInfo(studentId, memberRequest)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/mypage")
    public ResponseEntity deleteAccount(@RequestHeader("Authorization") String accessToken) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MYPAGE_DELETE, userService.deleteAccount(studentId)), HttpStatus.OK);
    }
}