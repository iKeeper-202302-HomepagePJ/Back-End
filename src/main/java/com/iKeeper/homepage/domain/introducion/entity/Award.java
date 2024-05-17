package com.iKeeper.homepage.domain.introducion.entity;

import com.iKeeper.homepage.domain.introducion.dto.AwardRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "award")
public class Award {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "award_id")
    private Long id;

    @Column(name = "award_name")
    private String name;

    @Column(name = "award_day")
    private LocalDate day;

    @Column(name = "award_content")
    private String content;

    @Column(name = "award_people")
    private String people;

    public void updateAward(String name, LocalDate day, String content, String people) {

        this.name = name;
        this.day = day;
        this.content = content;
        this.people = people;
    }

    @Builder
    public Award(String name, LocalDate day, String content, String people) {

        this.name = name;
        this.day = day;
        this.content = content;
        this.people = people;
    }

    public static Award createAward(AwardRequest awardRequest) {

        Award award = Award.builder()
                .name(awardRequest.getName())
                .day(awardRequest.getDay())
                .content(awardRequest.getContent())
                .people(awardRequest.getPeople())
                .build();
        return award;
    }
}
