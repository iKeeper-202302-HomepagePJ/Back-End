package com.iKeeper.homepage.domain.calendar.controller;

import com.iKeeper.homepage.domain.calendar.dto.request.CalendarRequest;
import com.iKeeper.homepage.domain.calendar.entity.Calendar;
import com.iKeeper.homepage.domain.calendar.service.CreateCalendarService;
import com.iKeeper.homepage.domain.calendar.service.DeleteCalendarService;
import com.iKeeper.homepage.domain.calendar.service.SearchCalendarService;
import com.iKeeper.homepage.domain.calendar.service.UpdateCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final CreateCalendarService createCalendarService;
    private final SearchCalendarService searchCalendarService;
    private final UpdateCalendarService updateCalendarService;
    private final DeleteCalendarService deleteCalendarService;

    @GetMapping(value = "")
    public ResponseEntity calendarList() {
        return ResponseEntity.ok(searchCalendarService.searchAllCalendar());
    }


    @GetMapping(value = "/search")
    public ResponseEntity searchCalendar(@RequestParam(value = "date", required = false)
                                             @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate day) {

        searchCalendarService.searchCalendar(day);
        return ResponseEntity.ok(searchCalendarService.searchCalendar(day));
    }

    @PostMapping(value = "")
    public String createCalendar(@RequestBody @Valid CalendarRequest calendarRequest,
                               BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().toString();
        }

        try {
            Calendar calendar = Calendar.createCalendar(calendarRequest);
            createCalendarService.createCalendar(calendar);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/";
    }

    @PatchMapping(value = "/{id}")
    public String updateCalendar(@PathVariable Long id,
                               @RequestBody @Valid CalendarRequest calendarRequest,
                               BindingResult bindingResult, Model model) {

        updateCalendarService.updateCalendar(id, calendarRequest);
        return "redirect:/";
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCalendar(@PathVariable Long id) {

        deleteCalendarService.deleteCalendar(id);
        return ResponseEntity.ok().build();
    }
}