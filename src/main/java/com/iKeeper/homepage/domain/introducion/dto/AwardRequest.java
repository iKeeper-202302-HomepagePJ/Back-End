package com.iKeeper.homepage.domain.introducion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class AwardRequest {

    private String name;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate day;

    private String content;

    private String people;

    public AwardRequest(String name, LocalDate day, String content, String people) {

        this.name = name;
        this.day = day;
        this.content = content;
        this.people = people;
    }
}
