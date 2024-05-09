package com.iKeeper.homepage.domain.post.entity;

import com.iKeeper.homepage.domain.post.dto.request.HeadlineRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "headline")
public class Headline {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "headline_id", nullable = false)
    private Byte id;

    @Column(name = "headline_name", length = 10)
    private String name;

    @Builder
    public Headline(String name) {

        this.name = name;
    }

    public static Headline createHeadline(HeadlineRequest headlineRequest) {
        Headline headline = Headline.builder()
                .name(headlineRequest.getName())
                .build();
        return headline;
    }
}