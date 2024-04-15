package com.iKeeper.homepage.domain.auth.dto.request;

import com.iKeeper.homepage.domain.user.entity.Grade;
import com.iKeeper.homepage.domain.user.entity.Major;
import com.iKeeper.homepage.domain.user.entity.Status;
import com.iKeeper.homepage.global.entity.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
public class SignUpRequest {

    @NotNull(message = "학번을 입력해주세요.")
    private String studentId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String pnumber;

    @NotBlank(message = "생년월일을 입력해주세요.")
    private String birth;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 특수문자를 포함한 8자 이상 20자 이하여야 합니다.")
    private String password;

    @NotNull(message = "분야를 선택해주세요.")
    private Field field;

    @NotNull(message = "재학 상태를 선택해주세요.")
    private Status status;

    @NotNull(message = "학년, 학차를 선택해주세요.")
    private Grade grade;

    @NotNull(message = "전공을 선택해주세요.")
    private Major major1;

    private Major major2;

    private Major major3;

    private Major minor;

    public SignUpRequest(String studentId, String name, String pnumber, String birth, String email,
                         String password, Field field, Status status, Grade grade, Major major,
                         Major major1, Major major2, Major major3, Major minor) {

        this.studentId = studentId;
        this.name = name;
        this.pnumber = pnumber;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.field = field;
        this.status = status;
        this.grade = grade;
        this.major1 = major1;
        this.major2 = major2;
        this.major3 = major3;
        this.minor = minor;
    }
}