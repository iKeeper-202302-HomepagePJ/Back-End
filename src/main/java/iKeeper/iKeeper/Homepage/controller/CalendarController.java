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

    @PostMapping("/read/{day}")
    public ResponseEntity readCalendars(@RequestParam(required = false) LocalDate day) {
        if (day == null) {
            return ResponseEntity.ok(calendarService.readCalendars());
        }
        return ResponseEntity.ok(calendarService.readCalendar(day));
    }

    @GetMapping("/read")
    public ResponseEntity readCalendars(@RequestParam(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.ok(calendarService.readCalendars());
        }
        return ResponseEntity.ok(calendarService.readCalendar(id));
    }

    /*@GetMapping("/schedule/{id}")
    public RequestEntity<Calendar> readCalendar (@PathVariable Long id) {
        return ResponseEntity.ok(calendarService.readCalendar(id));
    }*/

    @PostMapping("/create")
    public ResponseEntity<Calendar> createCalendar (@RequestBody CalendarCreationRequest request) {
        return ResponseEntity.ok(calendarService.createCalendar(request));
    }
}
