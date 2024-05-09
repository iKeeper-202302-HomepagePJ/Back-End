package com.iKeeper.homepage.domain.user.controller;

import com.iKeeper.homepage.domain.user.service.AdminMemberService;
import com.iKeeper.homepage.domain.user.service.UserService;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin/users")
@RequiredArgsConstructor
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

    @GetMapping(value = "/memberlist")
    public ResponseEntity memberList() {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.USER_MAJOR_LIST, adminMemberService.memberList()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{studentId}")
    public ResponseEntity deleteCalendar(@PathVariable String studentId) {

        adminMemberService.deleteMember(studentId);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.CALENDAR_DELETE), HttpStatus.OK);
    }
}
