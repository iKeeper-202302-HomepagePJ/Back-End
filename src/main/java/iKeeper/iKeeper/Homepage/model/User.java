package iKeeper.iKeeper.Homepage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name", length = 10)
    @ColumnDefault("'이름없음'")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_authority", referencedColumnName = "authority_id", nullable = false)
    Authority authority;

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
    Field Field;

    @ManyToOne
    @JoinColumn(name = "user_status", referencedColumnName = "status_id", nullable = false)
    Status status;

    @ManyToOne
    @JoinColumn(name = "user_grade", referencedColumnName = "grade_id", nullable = false)
    Status grade;

    @Column(name = "user_password", length = 20, nullable = false)
    @ColumnDefault("'i-KeeperD2509'")
    private String password;

    @Column(name = "user_warning")
    @ColumnDefault("'0'")
    private Boolean warning;

    @Column(name = "user_score_iKeeper")
    private Long iscore;

    @Column(name = "user_score_field")
    private Long fscore;

    @Column(name = "user_score_activity")
    private Long ascore;

    @Column(name = "user_score_etc")
    private Long escore;

    @Column(name = "user_score_sum")
    private Long sscore;

}