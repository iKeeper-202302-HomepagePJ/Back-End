package com.iKeeper.homepage.domain.attendance.dao;

import com.iKeeper.homepage.domain.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a "
            + "ORDER BY a.id DESC")
    List<Attendance> findByStudentId(String studentId);

    @Query("SELECT a FROM Attendance a where a.lecture.id = :lectureId ORDER by a.id DESC")
    List<Attendance> findByLectureId(Long lectureId);
}