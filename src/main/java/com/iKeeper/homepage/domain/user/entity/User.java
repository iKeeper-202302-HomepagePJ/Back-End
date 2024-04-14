package com.iKeeper.homepage.domain.user.entity;

import com.iKeeper.homepage.domain.auth.dto.request.SignUpRequest;
import com.iKeeper.homepage.global.entity.Field;
import com.iKeeper.homepage.global.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Entity
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_student", updatable = false, length = 10, nullable = false)
    private String student;

    @Column(name = "user_name", length = 10, nullable = false)
    private String name;

    @Column(name = "user_role", length = 5)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "user_field", referencedColumnName = "field_id", nullable = false)
    Field field;

    @Column(name = "user_phone_number", length = 15, nullable = false)
    private String pnumber;

    @Column(name = "user_birth", length = 10, nullable = false)
    private String birth;

    @Column(name = "user_email", length = 30, nullable = false)
    private String email;

    @Column(name = "user_password", length = 100, nullable = false)
    private String password;

    @Column(name = "user_status", length = 5, nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_grade", referencedColumnName = "grade_id", nullable = false)
    Grade grade;

    @OneToOne
    @JoinColumn(name = "major_id")
    private Major major;

    @Column(name = "user_warning", nullable = false)
    @ColumnDefault("False")
    private Boolean warning;

    public void updateName(String name) { this.name = name; }
    public void updateRole(UserRole role) { this.role = role; }
    public void updatePnumber(String pnumber) { this.pnumber = pnumber; }
    public void updateBirth(String birth) { this.birth = birth; }
    public void updateEmail(String email) { this.email = email; }
    public void updatePassword(String password) { this.password = password; }
    public void updateField(Field field) { this.field = field; }
    public void updateStatus(String status) { this.status = status; }
    public void updateGrade(Grade grade) { this.grade = grade; }

    @Builder
    public User(String student, String name, UserRole role, String pnumber, String birth, String email,
                String password, Field field, String status, Grade grade, Major major, Boolean warning) {
        this.student = student;
        this.name = name;
        this.role = role;
        this.pnumber = pnumber;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.field = field;
        this.status = status;
        this.grade = grade;
        this.major = major;
        this.warning = warning;
    }

    public static User createUser(SignUpRequest signUpRequest, PasswordEncoder passwordEncoder) {
        User user = User.builder()
                .student(signUpRequest.getStudent())
                .name(signUpRequest.getName())
                .role(UserRole.GUEST)
                .pnumber(signUpRequest.getPnumber())
                .birth(signUpRequest.getBirth())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .field(signUpRequest.getField())
                .status(signUpRequest.getStatus())
                .grade(signUpRequest.getGrade())
                .build();
        return user;
    }
}