package com.iKeeper.homepage.domain.user.dto.response;

import com.iKeeper.homepage.domain.user.entity.Member;
import lombok.Getter;

@Getter
public class StudentIdResponse {

    private String studentId;

    public StudentIdResponse(Member member) {

        this.studentId = member.getStudentId();
    }
}