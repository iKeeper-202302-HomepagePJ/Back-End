package iKeeper.iKeeper.Homepage.model.entity;

import iKeeper.iKeeper.Homepage.model.dto.UserFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@DynamicInsert
@Table(name = "user_table")
public class User {

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

    @Column(name = "user_profile_picture", length = 100)
    private String profile;

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
    @JoinColumn(name = "user_major", referencedColumnName = "major_id", nullable = false)
    Major major;

    @ManyToOne
    @JoinColumn(name = "user_minor1", referencedColumnName = "major_id", nullable = false)
    Major minor1;

    @ManyToOne
    @JoinColumn(name = "user_minor2", referencedColumnName = "major_id", nullable = false)
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

    @Builder
    public User(String student, String name, UserRole role, String pnumber, String birth, String email, String password, Major major, Major minor1, Major minor2, Field field, Status status, Grade grade) {
        this.student = student;
        this.name = name;
        this.role = role;
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

    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {
        User user = User.builder()
                .student(userFormDto.getStudent())
                .name(userFormDto.getName())
                .role(UserRole.USER)
                .pnumber(userFormDto.getPnumber())
                .birth(userFormDto.getBirth())
                .email(userFormDto.getEmail())
                .password(passwordEncoder.encode(userFormDto.getPassword()))
                .major(userFormDto.getMajor())
                .minor1(userFormDto.getMinor1())
                .minor2(userFormDto.getMinor2())
                .field(userFormDto.getField())
                .status(userFormDto.getStatus())
                .grade(userFormDto.getGrade())
                .build();
        return user;
    }
}
