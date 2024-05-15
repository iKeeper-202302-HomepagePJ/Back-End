package com.iKeeper.homepage.domain.calendar.entity;
import com.iKeeper.homepage.domain.calendar.dto.request.CalendarRequest;
import com.iKeeper.homepage.domain.user.entity.Field;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "calendar")
public class Calendar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id", nullable = false)
    private Long id;

    @Column(name = "calendar_title", length = 30, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "calendar_field")
    private Field field;

    @Column(name = "calendar_day", nullable = false)
    private LocalDate day;

    @Column(name = "calendar_time", nullable = false)
    private Time time;

    @Column(name = "calendar_place")
    @ColumnDefault("'미정'")
    private String place;

    @Column(name = "calendar_check")
    @ColumnDefault("False")
    private Boolean check;

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateField(Field field) {
        this.field = field;
    }

    public void updateDay(LocalDate day) {
        this.day = day;
    }

    public void updateTime(Time time) {
        this.time = time;
    }

    public void updatePlace(String place) {
        this.place = place;
    }

    public void updateCheck(Boolean check) {
        this.check = check;
    }

    @Builder
    public Calendar(String title, Field field, LocalDate day, Time time, String place, Boolean check) {

        this.title = title;
        this.field = field;
        this.day = day;
        this.time = time;
        this.place = place;
        this.check = check;
    }

    public static Calendar createCalendar(CalendarRequest calendarRequest) {
        Calendar calendar = Calendar.builder()
                .title(calendarRequest.getTitle())
                .field(calendarRequest.getField())
                .day(calendarRequest.getDay())
                .time(calendarRequest.getTime())
                .place(calendarRequest.getPlace())
                .check(Boolean.FALSE)
                .build();
        return calendar;
    }
}