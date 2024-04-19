package com.iKeeper.homepage.domain.calendar.service;

import com.iKeeper.homepage.domain.calendar.dao.CalendarRepository;
import com.iKeeper.homepage.domain.calendar.dto.request.CalendarRequest;
import com.iKeeper.homepage.domain.calendar.entity.Calendar;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public List<Calendar> searchAllCalendar() {
        return calendarRepository.findAll();
    }

    public List<Calendar> searchCalendar(LocalDate day) {

        List<Calendar> searchCalendars = calendarRepository.findAllByDay(day);
        return searchCalendars;
    }

    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    @Transactional
    public Long updateCalendar(Long id, CalendarRequest calendarRequest) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new CustomException("해당 ID의 일정이 존재하지 않습니다.", ErrorCode.CALENDAR_NOT_FOUND));

        calendar.updateTitle(calendarRequest.getTitle());
        calendar.updateField(calendarRequest.getField());
        calendar.updateDay(calendarRequest.getDay());
        calendar.updateTime(calendarRequest.getTime());
        calendar.updatePlace(calendarRequest.getPlace());
        calendar.updateCheck(calendarRequest.getCheck());
        return id;
    }

    public void deleteCalendar(Long id) {
        calendarRepository.deleteById(id);
    }


}