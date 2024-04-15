package com.iKeeper.homepage.domain.user.entity;

import com.iKeeper.homepage.domain.auth.dto.request.SignUpRequest;
import com.iKeeper.homepage.global.entity.Field;
import com.iKeeper.homepage.global.entity.UserRole;
import jdk.jshell.Snippet;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "member_student_id", nullable = false)
    private String studentId;

    @Column(name = "member_name", length = 10, nullable = false)
    private String name;

    @Column(name = "member_role", length = 5)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "member_field", referencedColumnName = "field_id", nullable = false)
    Field field;

    @Column(name = "member_phone_number", length = 15, nullable = false)
    private String pnumber;

    @Column(name = "member_birth", length = 10, nullable = false)
    private String birth;

    @Column(name = "member_email", length = 30, nullable = false)
    private String email;

    @Column(name = "member_password", length = 100, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "member_status", referencedColumnName = "status_id", nullable = false)
    Status status;

    @ManyToOne
    @JoinColumn(name = "member_grade", referencedColumnName = "grade_id", nullable = false)
    Grade grade;

    @ManyToOne
    @JoinColumn(name = "member_major1", referencedColumnName = "major_id", nullable = false)
    Major major1;

    @ManyToOne
    @JoinColumn(name = "member_major2", referencedColumnName = "major_id", nullable = false)
    Major major2;

    @ManyToOne
    @JoinColumn(name = "member_major3", referencedColumnName = "major_id", nullable = false)
    Major major3;

    @ManyToOne
    @JoinColumn(name = "member_minor", referencedColumnName = "major_id", nullable = false)
    Major minor;

    @Column(name = "member_warning", nullable = false)
    @ColumnDefault("'0'")
    private Boolean warning;

    public void updateName(String name) { this.name = name; }
    public void updateRole(UserRole role) { this.role = role; }
    public void updatePnumber(String pnumber) { this.pnumber = pnumber; }
    public void updateBirth(String birth) { this.birth = birth; }
    public void updateEmail(String email) { this.email = email; }
    public void updatePassword(String password) { this.password = password; }
    public void updateField(Field field) { this.field = field; }
    public void updateStatus(Status status) { this.status = status; }
    public void updateGrade(Grade grade) { this.grade = grade; }
    public void updateMajor1(Major major1) { this.major1 = major1; }
    public void updateMajor2(Major major2) { this.major2 = major2; }
    public void updateMajor3(Major major3) { this.major3 = major3; }
    public void updateMinor(Major minor) { this.minor = minor; }
    public void updateWarning(Boolean warning) { this.warning = warning; }

    @Builder
    public Member(String studentId, String name, UserRole role, String pnumber, String birth,
                  String email, Major major1, Major major2, Major major3, Major minor,
                  Field field, Status status, Grade grade, Boolean warning) {

        this.studentId = studentId;
        this.name = name;
        this.role = role;
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
        this.warning = warning;
    }

    public static Member createUser(SignUpRequest signUpRequest, PasswordEncoder passwordEncoder) {

        Member member = Member.builder()
                .studentId(signUpRequest.getStudentId())
                .name(signUpRequest.getName())
                .role(UserRole.GUEST)
                .pnumber(signUpRequest.getPnumber())
                .birth(signUpRequest.getBirth())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .field(signUpRequest.getField())
                .status(signUpRequest.getStatus())
                .grade(signUpRequest.getGrade())
                .major1(signUpRequest.getMajor1())
                .major2(signUpRequest.getMajor2())
                .major3(signUpRequest.getMajor3())
                .minor(signUpRequest.getMinor())
                .warning(Boolean.FALSE)
                .build();
        return member;
    }
}