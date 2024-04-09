package com.iKeeper.homepage.domain.calendar.service;

import com.iKeeper.homepage.domain.calendar.dao.CalendarRepository;
import com.iKeeper.homepage.domain.calendar.entity.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchCalendarService {

    private final CalendarRepository calendarRepository;

    public List<Calendar> searchAllCalendar() {
        return calendarRepository.findAll();
    }

    public List<Calendar> searchCalendar(LocalDate day) {

        List<Calendar> searchCalendars = calendarRepository.findAllByDay(day);
        return searchCalendars;
    }
}