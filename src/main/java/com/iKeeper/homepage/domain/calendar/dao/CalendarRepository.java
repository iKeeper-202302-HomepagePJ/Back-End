package com.iKeeper.homepage.domain.calendar.dao;

import com.iKeeper.homepage.domain.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findAllByDay(LocalDate day);
}