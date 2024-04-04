package iKeeper.iKeeper.Homepage.controller;

import iKeeper.iKeeper.Homepage.model.entity.Calendar;
import iKeeper.iKeeper.Homepage.model.request.CalendarCreationRequest;
import iKeeper.iKeeper.Homepage.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("")
    public ResponseEntity readCalendars() {
        return ResponseEntity.ok(calendarService.readCalendars());
    } // 현재 DB에 저장된 모든 일정 리스트 출력

    @GetMapping("/{id}")
    public ResponseEntity readCalendar(@PathVariable Long id) {
        return ResponseEntity.ok(calendarService.readCalendar(id));
    } // 현재 DB에 저장된 모든 일정 리스트 출력

    @PostMapping("")
    public ResponseEntity<Calendar> createCalendar (@Valid @RequestBody BindingResult bindingResult, CalendarCreationRequest request) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.createCalendar(request));
    } // DB에 일정 정보 저장

    @PatchMapping("/{id}")
    public ResponseEntity<Calendar> updateCalendar (@Valid @RequestBody BindingResult bindingResult, CalendarCreationRequest request, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(calendarService.updateCalendar(id, request));
    } // DB에 저장된 일정의 정보 수정

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendar (@PathVariable Long id) {
        calendarService.deleteCalendar(id);
        return ResponseEntity.ok().build();

    } // DB에 저장된 일정 삭제
}
