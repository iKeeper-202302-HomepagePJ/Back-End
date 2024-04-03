package iKeeper.iKeeper.Homepage.model.dto;

import iKeeper.iKeeper.Homepage.model.entity.Field;
import iKeeper.iKeeper.Homepage.model.entity.Grade;
import iKeeper.iKeeper.Homepage.model.entity.Major;
import iKeeper.iKeeper.Homepage.model.entity.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
public class UserFormDto {

    @NotBlank(message = "학번을 입력해주세요.")
    private String student;

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
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 특수문자를 포함한 8자 이상 16자 이하여야 합니다.")
    private String password;

    @NotNull(message = "전공을 선택해주세요.")
    private Major major;

    @NotNull(message = "부전공을 선택해주세요.")
    private Major minor1;

    @NotNull(message = "부전공을 선택해주세요.")
    private Major minor2;

    @NotNull(message = "분야를 선택해주세요.")
    private Field field;

    @NotNull(message = "재학 상태를 선택해주세요.")
    private Status status;

    @NotNull(message = "학년과 학차를 선택해주세요.")
    private Grade grade;

    public UserFormDto(String student, String name, String pnumber, String birth, String email, String password, Major major, Major minor1, Major minor2, Field field, Status status, Grade grade) {
        this.student = student;
        this.name = name;
        this.pnumber = pnumber;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.major = major;
        this.minor1 = minor1;
        this.minor2 = minor2;
        this.field = field;
        this.status = status;
        this.grade = grade;
    }

}
