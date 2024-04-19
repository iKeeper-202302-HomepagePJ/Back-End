package com.iKeeper.homepage.domain.calendar.controller;

import com.iKeeper.homepage.domain.calendar.dto.request.CalendarRequest;
import com.iKeeper.homepage.domain.calendar.entity.Calendar;
import com.iKeeper.homepage.domain.calendar.service.CalendarService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping(value = "")
    public ResponseEntity calendarList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.CALENDAR_READ_ALL, calendarService.searchAllCalendar()), HttpStatus.OK);
    }


    @GetMapping(value = "/search")
    public ResponseEntity searchCalendar(@RequestParam(value = "date", required = false)
                                             @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate day) {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.CALENDAR_READ_DATE, calendarService.searchCalendar(day)), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity createCalendar(@RequestBody @Valid CalendarRequest calendarRequest,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("일부 입력된 값이 올바르지 않습니다.", ErrorCode.CALENDAR_INVALID_VALUE);
        }

        else {
            Calendar calendar = Calendar.createCalendar(calendarRequest);
            calendarService.createCalendar(calendar);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.CALENDAR_POST, calendarRequest), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity updateCalendar(@PathVariable Long id,
                               @RequestBody @Valid CalendarRequest calendarRequest,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("일부 입력된 값이 올바르지 않습니다.", ErrorCode.CALENDAR_INVALID_VALUE);
        }

        else {
            calendarService.updateCalendar(id, calendarRequest);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                    ResponseMessage.CALENDAR_PATCH, calendarRequest), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCalendar(@PathVariable Long id) {

        calendarService.deleteCalendar(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.CALENDAR_DELETE), HttpStatus.OK);
    }
}