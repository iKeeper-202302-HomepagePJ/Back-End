package com.iKeeper.homepage.domain.user.controller;

import com.iKeeper.homepage.domain.user.dto.request.MemberRequest;
import com.iKeeper.homepage.domain.user.service.AdminMemberService;
import com.iKeeper.homepage.domain.user.service.UserService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/members")
@RequiredArgsConstructor
public class AdminMemberController {

    private final AdminMemberService adminMemberService;
    private final UserService userService;

    @GetMapping(value = "")
    public ResponseEntity memberList() {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MAJOR_LIST, adminMemberService.memberList()), HttpStatus.OK);
    }

    @PatchMapping(value = "/{studentId}")
    public ResponseEntity updateMemberInfo(@PathVariable String studentId, @RequestBody @Valid MemberRequest memberRequest,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("일부 입력된 값이 올바르지 않습니다.", ErrorCode.USER_INVALID_VALUE);
        }

        userService.updateMemberInfo(studentId, memberRequest);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MYPAGE_PATCH, userService.updateMemberInfo(studentId, memberRequest)), HttpStatus.OK);
    }

    @PatchMapping(value = "/role/user/{studentId}")
    public ResponseEntity approvalJoin(@PathVariable String studentId) {

        adminMemberService.updateRoleUser(studentId);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.CALENDAR_DELETE), HttpStatus.OK);
    }

    @PatchMapping(value = "/role/admin/{studentId}")
    public ResponseEntity grantRoleAdmin(@PathVariable String studentId) {

        adminMemberService.updateRoleAdmin(studentId);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.CALENDAR_DELETE), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{studentId}")
    public ResponseEntity deleteCalendar(@PathVariable String studentId) {

        adminMemberService.deleteMember(studentId);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.CALENDAR_DELETE), HttpStatus.OK);
    }
}
