package com.iKeeper.homepage.domain.user.dto.request;

import com.iKeeper.homepage.domain.user.entity.Grade;
import com.iKeeper.homepage.domain.user.entity.Major;
import com.iKeeper.homepage.domain.user.entity.Status;
import com.iKeeper.homepage.global.entity.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
public class UserRequest {

    private String student;

    private String name;

    private String pnumber;

    private String birth;

    private String email;

    private Field field;

    private Status status;

    private Grade grade;

    private Major major1;

    private Major major2;

    private Major major3;

    private Major minor;

    public UserRequest(String student, String name, String pnumber, String birth, String email, Field field,
                       Status status, Grade grade, Major major1, Major major2, Major major3, Major minor) {

        this.student = student;
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