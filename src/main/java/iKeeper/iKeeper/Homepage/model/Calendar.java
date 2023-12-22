package iKeeper.iKeeper.Homepage.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.*;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "calendar_table")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "calendar_id", nullable = false)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "field_id", nullable = false)
    //private Field field;

    @Column(name = "calendar_title", nullable = false, length = 30)
    @ColumnDefault("'일정명이 존재하지 않습니다.'")
    private String title;

    @Column(name = "calendar_day", nullable = false)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate day;

    @Column(name = "calendar_time", nullable = false)
    @DateTimeFormat(pattern = "HH:MM:SS")
    private LocalTime time;

    @Column(name = "calendar_place", columnDefinition = "varchar(10) default '미정'")
    private String place;

    @Column(name = "calendar_check", nullable = false)
    @ColumnDefault("0")
    private Boolean check;
}