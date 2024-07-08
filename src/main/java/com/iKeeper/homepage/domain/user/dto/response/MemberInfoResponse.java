package com.iKeeper.homepage.domain.user.dto.response;

import com.iKeeper.homepage.domain.user.entity.*;
import lombok.Getter;

@Getter
public class MemberInfoResponse {

    private String studentId;

    private String name;

    private Field field;

    private Boolean warning;

    public MemberInfoResponse(Member member) {

        this.studentId = member.getStudentId();
        this.name = member.getName();
        this.field = member.getField();
        this.warning = member.getWarning();
    }
}
