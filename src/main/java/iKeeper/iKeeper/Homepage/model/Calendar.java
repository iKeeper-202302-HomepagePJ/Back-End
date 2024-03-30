package iKeeper.iKeeper.Homepage.model;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "calendar_table")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id", nullable = false)
    private Short id;

    @ManyToOne
    @JoinColumn(name = "calendar_field", referencedColumnName = "field_id", nullable = false)
    Field field;

    @Column(name = "calendar_title", length = 30)
    @ColumnDefault("'일정명이 존재하지 않습니다.'")
    private String title;

    @Column(name = "calendar_day", nullable = false)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate day;

    @Column(name = "calendar_time", nullable = false)
    private Time time;

    @Column(name = "calendar_place")
    @ColumnDefault("'미정'")
    private String place;

    @Column(name = "calendar_check")
    @ColumnDefault("'0'")
    private Boolean check;
}