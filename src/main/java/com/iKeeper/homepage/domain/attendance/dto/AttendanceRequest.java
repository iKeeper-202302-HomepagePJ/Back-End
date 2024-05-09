package com.iKeeper.homepage.domain.attendance.dto;

import com.iKeeper.homepage.domain.attendance.entity.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AttendanceRequest {

    @NotNull
    private String studentId;

    @NotNull
    private Lecture lecture;

    public AttendanceRequest(String studentId, Lecture lecture) {

        this.studentId = studentId;
        this.lecture = lecture;
    }
}
