package com.iKeeper.homepage.domain.attendance.service;

import com.iKeeper.homepage.domain.attendance.dao.AttendanceRepository;
import com.iKeeper.homepage.domain.attendance.dao.LectureRepository;
import com.iKeeper.homepage.domain.attendance.dto.request.AttendanceRequest;
import com.iKeeper.homepage.domain.attendance.entity.Attendance;
import com.iKeeper.homepage.domain.attendance.entity.Lecture;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final LectureRepository lectureRepository;

    public List<Lecture> getLectureList() {
        return lectureRepository.findAll();
    }

    public List<Attendance> getAttendanceByLectureId(Long lectureId) {
        return attendanceRepository.findByLectureId(lectureId);
    }

    public List<Attendance> getAttendanceByStudentId(String studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public Lecture createLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Transactional
    public void updateAttendance(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new CustomException("해당 학번의 유저가 존재하지 않습니다.", ErrorCode.USER_MEMBER_NOT_FOUND));

        attendance.updateCheck(Boolean.TRUE);
    }

    public void deleteAllLecture() {
        lectureRepository.deleteAll();
    }

    public void deleteAllAttendance() {
        attendanceRepository.deleteAll();
    }
}
