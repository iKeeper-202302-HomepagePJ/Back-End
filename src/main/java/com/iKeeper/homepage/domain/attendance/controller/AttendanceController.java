package com.iKeeper.homepage.domain.attendance.controller;

import com.iKeeper.homepage.domain.attendance.dto.AttendanceRequest;
import com.iKeeper.homepage.domain.attendance.entity.Attendance;
import com.iKeeper.homepage.domain.attendance.service.AttendanceService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping(value = "")
    public ResponseEntity createAttendance(@RequestBody @Valid AttendanceRequest attendanceRequest,
                                           BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.AUTH_INVALID_VALUE);
        }

        Attendance attendance = Attendance.createAttendance(attendanceRequest);
        attendanceService.createAttendance(attendance);
        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.CALENDAR_POST, attendanceRequest), HttpStatus.CREATED);
    }
}
