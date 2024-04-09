package com.iKeeper.homepage.domain.calendar.service;

import com.iKeeper.homepage.domain.calendar.dao.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCalendarService {

    private final CalendarRepository calendarRepository;

    public void deleteCalendar(Long id) {
        calendarRepository.deleteById(id);
    }
}
