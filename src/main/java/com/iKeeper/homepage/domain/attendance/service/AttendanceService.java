package com.iKeeper.homepage.domain.attendance.service;

import com.iKeeper.homepage.domain.attendance.dao.AttendanceRepository;
import com.iKeeper.homepage.domain.attendance.entity.Attendance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public Attendance createAttendance(Attendance attendance) {

        return attendanceRepository.save(attendance);
    }
}
