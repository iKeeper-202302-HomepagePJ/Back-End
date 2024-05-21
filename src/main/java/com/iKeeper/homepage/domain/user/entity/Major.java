package com.iKeeper.homepage.domain.user.entity;

import com.iKeeper.homepage.domain.user.dto.request.MajorRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "major")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id", nullable = false)
    private Long id;

    @Column(name = "major_name", nullable = false)
    private String name;

    @Builder
    public Major(String name) {
        this.name = name;
    }

    public static Major createMajor(MajorRequest majorRequest) {
        Major major = Major.builder()
                .name(majorRequest.getName())
                .build();
        return major;
    }
}