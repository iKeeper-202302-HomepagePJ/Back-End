package com.iKeeper.homepage.domain.calendar.service;

import com.iKeeper.homepage.domain.calendar.dao.CalendarRepository;
import com.iKeeper.homepage.domain.calendar.entity.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateCalendarService {

    private final CalendarRepository calendarRepository;

    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }
}