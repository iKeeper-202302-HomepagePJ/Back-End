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

    private Major major;

    private Field field;

    private Status status;

    private Grade grade;

    public UserRequest(String student, String name, String pnumber, String birth, String email,
                         Field field, Status status, Grade grade, Major major) {

        this.student = student;
        this.name = name;
        this.pnumber = pnumber;
        this.birth = birth;
        this.email = email;
        this.field = field;
        this.status = status;
        this.grade = grade;
        this.major = major;
    }

}