package com.iKeeper.homepage.domain.introducion.entity;

import com.iKeeper.homepage.domain.introducion.dto.IntroduceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "introduce")
public class Introduce {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "introduce_id")
    private Long id;

    @Column(name = "introduce_content")
    private String content;

    public void updateContent(String content) {

        this.content = content;
    }

    @Builder
    public Introduce(String content) {

        this.content = content;
    }

    public static Introduce createIntroduce(IntroduceRequest introduceRequest) {

        Introduce introduce = Introduce.builder()
                .content(introduceRequest.getContent())
                .build();
        return introduce;
    }
}
