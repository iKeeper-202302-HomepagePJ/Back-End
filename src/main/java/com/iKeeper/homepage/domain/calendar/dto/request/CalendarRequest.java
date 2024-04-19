package com.iKeeper.homepage.domain.calendar.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iKeeper.homepage.global.entity.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CalendarRequest {
    @NotBlank(message = "일정명을 입력해주세요.")
    private String title;

    @NotNull(message = "분야를 입력해주세요.")
    private Field field;

    @NotNull(message = "날짜를 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDate day;

    @NotNull(message = "시간을 입력해주세요.")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private Time time;

    @NotNull(message = "장소를 입력해주세요.")
    private String place;

    private Boolean check;

    public CalendarRequest(String title, Field field, LocalDate day, Time time, String place, Boolean check) {
        this.title = title;
        this.field = field;
        this.day = day;
        this.time = time;
        this.place = place;
        this.check = check;
    }
}