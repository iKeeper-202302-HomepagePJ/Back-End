package iKeeper.iKeeper.Homepage.controller;

import iKeeper.iKeeper.Homepage.model.Calendar;
import iKeeper.iKeeper.Homepage.model.request.CalendarCreationRequest;
import iKeeper.iKeeper.Homepage.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/read")
    public ResponseEntity readCalendars(@RequestParam(required = false)Long id) {
        if (id == null) {
            return ResponseEntity.ok(calendarService.readCalendars());
        }
        return ResponseEntity.ok(calendarService.readCalendar(id));
    } // 현재 DB에 저장된 모든 일정 리스트 출력


    @PostMapping("/create")
    public ResponseEntity<Calendar> createCalendar (@RequestBody CalendarCreationRequest request) {
        return ResponseEntity.ok(calendarService.createCalendar(request));
    } // DB에 일정 정보 저장

    @PatchMapping("/update/{id}")
    public ResponseEntity<Calendar> updateCalendar (@RequestBody CalendarCreationRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(calendarService.updateCalendar(id, request));
    } // DB에 저장된 일정의 정보 수정

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCalendar (@PathVariable Long id) {
        calendarService.deleteCalendar(id);
        return ResponseEntity.ok().build();

    } // DB에 저장된 일정 삭제
}
