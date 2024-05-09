package com.iKeeper.homepage.domain.attendance.entity;

import com.iKeeper.homepage.domain.attendance.dto.AttendanceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "attendance")
public class Attendance {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @Column(name = "attendance_student_id")
    private String studentId;

    @ManyToOne
    @JoinColumn(name = "attendance_lecture_id")
    private Lecture lecture;

    @Column(name = "attendance_check")
    private Boolean check;

    @Builder
    public Attendance(String studentId, Lecture lecture, Boolean check) {

        this.studentId = studentId;
        this.lecture = lecture;
        this.check = check;
    }

    public static Attendance createAttendance(AttendanceRequest attendanceRequest) {

        Attendance attendance = Attendance.builder()
                .studentId(attendanceRequest.getStudentId())
                .lecture(attendanceRequest.getLecture())
                .check(Boolean.FALSE)
                .build();
        return attendance;
    }
}
