package iKeeper.iKeeper.Homepage.controller;

import iKeeper.iKeeper.Homepage.model.Calendar;
import iKeeper.iKeeper.Homepage.model.request.CalendarCreationRequest;
import iKeeper.iKeeper.Homepage.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/read")
    public ResponseEntity readCalendars(@RequestParam(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.ok(calendarService.readCalendars());
        }
        return ResponseEntity.ok(calendarService.readCalendar(id));
    }

    @PostMapping("/read/{day}")
    public ResponseEntity readCalendars(@RequestParam(required = false) LocalDate day) {
        if (day == null) {
            return ResponseEntity.ok(calendarService.readCalendars());
        }
        return ResponseEntity.ok(calendarService.readCalendar(day));
    }

    @PostMapping("/create")
    public ResponseEntity<Calendar> createCalendar (@RequestBody CalendarCreationRequest request) {
        return ResponseEntity.ok(calendarService.createCalendar(request));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Calendar> updateCalendar (@RequestBody CalendarCreationRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(calendarService.updateCalendar(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCalendar (@PathVariable Long id) {
        //CalendarService.deleteCalendar(id);
        return ResponseEntity.ok().build();

    }
}
