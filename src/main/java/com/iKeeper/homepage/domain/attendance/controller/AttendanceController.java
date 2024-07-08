package com.iKeeper.homepage.domain.attendance.controller;

import com.iKeeper.homepage.domain.attendance.dao.LectureRepository;
import com.iKeeper.homepage.domain.attendance.dto.request.AttendanceRequest;
import com.iKeeper.homepage.domain.attendance.dto.request.LectureRequest;
import com.iKeeper.homepage.domain.attendance.entity.Attendance;
import com.iKeeper.homepage.domain.attendance.entity.Lecture;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping(value = "/lecture")
    public ResponseEntity getLectureList() {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.ATTENDANCE_LECTURE_LIST, attendanceService.getLectureList()), HttpStatus.OK);
    }

    @GetMapping(value = "/lecture/{lectureId}")
    public ResponseEntity getAttendanceByLectureId(@PathVariable Long lectureId) {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.ATTENDANCE_LECTURE, attendanceService.getAttendanceByLectureId(lectureId)), HttpStatus.OK);
    }

    @GetMapping(value = "/student/{studentId}")
    public ResponseEntity getAttendanceByStudentId(@PathVariable String studentId) {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.ATTENDANCE_USER, attendanceService.getAttendanceByStudentId(studentId)), HttpStatus.OK);
    }

    @PostMapping(value = "/lecture")
    public ResponseEntity createLecture(@RequestBody @Valid LectureRequest lectureRequest,
                                        BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.AUTH_INVALID_VALUE);
        }

        Lecture lecture = Lecture.createLecture(lectureRequest);
        attendanceService.createLecture(lecture);
        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.ATTENDANCE_LECTURE_POST), HttpStatus.CREATED);
    }

    @PostMapping(value = "")
    public ResponseEntity createAttendance(@RequestBody @Valid AttendanceRequest attendanceRequest,
                                           BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.AUTH_INVALID_VALUE);
        }

        Attendance attendance = Attendance.createAttendance(attendanceRequest);
        attendanceService.createAttendance(attendance);
        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.ATTENDANCE_POST), HttpStatus.CREATED);
    }

    @PatchMapping(value = "")
    public ResponseEntity updateAttendance(@RequestParam("id") List<Long> ids) {

        for (Long id : ids) attendanceService.updateAttendance(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.ATTENDANCE_PATCH), HttpStatus.OK);
    }

    @DeleteMapping(value = "/lecture")
    public ResponseEntity deleteAllLecture() {

        attendanceService.deleteAllLecture();
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.ATTENDANCE_LECTURE_DELETE), HttpStatus.OK);
    }

    @DeleteMapping(value = "")
    public ResponseEntity deleteAllAttendance() {

        attendanceService.deleteAllAttendance();
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.ATTENDANCE_DELETE), HttpStatus.OK);
    }
}
