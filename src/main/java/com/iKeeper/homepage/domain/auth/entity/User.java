package com.iKeeper.homepage.domain.auth.entity;

import com.iKeeper.homepage.domain.auth.dto.request.UserSignUpRequest;
import com.iKeeper.homepage.global.entity.Field;
import com.iKeeper.homepage.global.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "student_id", length = 10)
    private String student;

    @Column(name = "user_name", length = 10)
    @ColumnDefault("'이름없음'")
    private String name;

    @Column(name = "user_role", length = 5)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "user_phone_number", length = 15, nullable = false)
    @ColumnDefault("'010-0000-0000'")
    private String pnumber;

    @Column(name = "user_birth", length = 10, nullable = false)
    @ColumnDefault("'YYYYMMDD'")
    private String birth;

    @Column(name = "user_email", length = 30, nullable = false)
    @ColumnDefault("'iKeeper@cu.ac.kr'")
    private String email;

    @Column(name = "user_password", length = 20, nullable = false)
    @ColumnDefault("'i-KeeperD2509'")
    private String password;

    @ManyToOne
    @JoinColumn(name = "major_main", referencedColumnName = "major_id", nullable = false)
    Major major1;

    @ManyToOne
    @JoinColumn(name = "major_double", referencedColumnName = "major_id", nullable = false)
    Major major2;

    @ManyToOne
    @JoinColumn(name = "minor_first", referencedColumnName = "major_id", nullable = false)
    Major minor1;

    @ManyToOne
    @JoinColumn(name = "minor_second", referencedColumnName = "major_id", nullable = false)
    Major minor2;

    @ManyToOne
    @JoinColumn(name = "user_field", referencedColumnName = "field_id", nullable = false)
    Field field;

    @ManyToOne
    @JoinColumn(name = "user_status", referencedColumnName = "status_id", nullable = false)
    Status status;

    @ManyToOne
    @JoinColumn(name = "user_grade", referencedColumnName = "grade_id", nullable = false)
    Grade grade;

    @Column(name = "user_warning")
    @ColumnDefault("'0'")
    private Boolean warning;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return student;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder
    public User(String student, String name, UserRole role, String pnumber, String birth, String email,
                String password, Major major1, Major major2, Major minor1, Major minor2, Field field,
                Status status, Grade grade) {
        this.student = student;
        this.name = name;
        this.role = role;
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

    public static User createUser(UserSignUpRequest userSignUpRequest, PasswordEncoder passwordEncoder) {
        User user = User.builder()
                .student(userSignUpRequest.getStudent())
                .name(userSignUpRequest.getName())
                .role(UserRole.USER)
                .pnumber(userSignUpRequest.getPnumber())
                .birth(userSignUpRequest.getBirth())
                .email(userSignUpRequest.getEmail())
                .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                .major1(userSignUpRequest.getMajor1())
                .major2(userSignUpRequest.getMajor2())
                .minor1(userSignUpRequest.getMinor1())
                .minor2(userSignUpRequest.getMinor2())
                .field(userSignUpRequest.getField())
                .status(userSignUpRequest.getStatus())
                .grade(userSignUpRequest.getGrade())
                .build();
        return user;
    }
}