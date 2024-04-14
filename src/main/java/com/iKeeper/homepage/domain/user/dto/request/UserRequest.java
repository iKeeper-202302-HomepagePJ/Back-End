package com.iKeeper.homepage.domain.user.dto.request;

import com.iKeeper.homepage.domain.user.entity.Grade;
import com.iKeeper.homepage.domain.user.entity.Major;
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

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 특수문자를 포함한 8자 이상 16자 이하여야 합니다.")
    private String password;

    private Major major1;

    private Major major2;

    private Major minor1;

    private Major minor2;

    private Field field;

    private String status;

    private Grade grade;

    public UserRequest(String student, String name, String pnumber, String birth, String email,
                         String password, Major major1, Major major2, Major minor1, Major minor2,
                         Field field, String status, Grade grade) {

        this.student = student;
        this.name = name;
        this.pnumber = pnumber;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.major1 = major1;
        this.major2 = major2;
        this.minor1 = minor1;
        this.minor2 = minor2;
        this.field = field;
        this.status = status;
        this.grade = grade;
    }

}