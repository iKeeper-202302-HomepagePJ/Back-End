package com.iKeeper.homepage.domain.user.dto.request;

import com.iKeeper.homepage.domain.user.entity.Grade;
import com.iKeeper.homepage.domain.user.entity.Major;
import com.iKeeper.homepage.domain.user.entity.Status;
import com.iKeeper.homepage.domain.user.entity.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
public class MemberRequest {

    private String studentId;

    private String name;

    @Pattern(regexp = "[0-9]{3}-[0-9]{3,4}-[0-9]{4}")
    private String pnumber;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private String birth;

    @Email
    private String email;

    private Field field;

    private Status status;

    private Grade grade;

    private Major major1;

    private Major major2;

    private Major major3;

    private Major minor;

    public MemberRequest(String studentId, String name, String pnumber, String birth, String email, Field field,
                         Status status, Grade grade, Major major1, Major major2, Major major3, Major minor) {

        this.studentId = studentId;
        this.name = name;
        this.pnumber = pnumber;
        this.birth = birth;
        this.email = email;
        this.field = field;
        this.status = status;
        this.grade = grade;
        this.major1 = major1;
        this.major2 = major2;
        this.major3 = major3;
        this.minor = minor;
    }
}