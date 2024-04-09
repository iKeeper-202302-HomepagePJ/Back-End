package com.iKeeper.homepage.domain.calendar.service;

import com.iKeeper.homepage.domain.calendar.dao.CalendarRepository;
import com.iKeeper.homepage.domain.calendar.dto.request.CalendarRequest;
import com.iKeeper.homepage.domain.calendar.entity.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCalendarService {

    private final CalendarRepository calendarRepository;

    @Transactional
    public Long updateCalendar(Long id, CalendarRequest calendarRequest) {
        Calendar calendar = calendarRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("해당 번호의 일정이 존재하지 않습니다."));

        calendar.updateTitle(calendarRequest.getTitle());
        calendar.updateField(calendarRequest.getField());
        calendar.updateDay(calendarRequest.getDay());
        calendar.updateTime(calendarRequest.getTime());
        calendar.updatePlace(calendarRequest.getPlace());
        calendar.updateCheck(calendarRequest.getCheck());
        return id;
    }
}