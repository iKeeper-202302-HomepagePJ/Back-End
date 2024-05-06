package com.iKeeper.homepage.domain.user.dto.response;

import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.domain.user.entity.Grade;
import com.iKeeper.homepage.domain.user.entity.Major;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.domain.user.entity.Status;
import com.iKeeper.homepage.global.entity.Field;
import com.iKeeper.homepage.global.entity.UserRole;
import lombok.Getter;

@Getter
public class MemberListResponse {

    private String studentId;

    private String name;

    private UserRole role;

    private Field field;

    private String pnumber;

    private String birth;

    private String email;

    private Status status;

    private Grade grade;

    private Major major1;

    private Major major2;

    private Major major3;

    private Major minor;

    private Boolean warning;

    public MemberListResponse(Member member) {

        this.studentId = member.getStudentId();
        this.name = member.getName();
        this.role = member.getRole();
        this.pnumber = member.getPnumber();
        this.birth = member.getBirth();
        this.email = member.getEmail();
        this.field = member.getField();
        this.status = member.getStatus();
        this.grade = member.getGrade();
        this.major1 = member.getMajor1();
        this.major2 = member.getMajor2();
        this.major3 = member.getMajor3();
        this.minor = member.getMinor();
        this.warning = member.getWarning();
    }
}
