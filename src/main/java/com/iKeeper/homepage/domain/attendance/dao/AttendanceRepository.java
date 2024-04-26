package com.iKeeper.homepage.domain.attendance.dao;

import com.iKeeper.homepage.domain.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
