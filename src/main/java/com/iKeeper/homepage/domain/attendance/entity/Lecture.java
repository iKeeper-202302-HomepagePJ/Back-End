package com.iKeeper.homepage.domain.attendance.entity;

import com.iKeeper.homepage.domain.attendance.dto.request.LectureRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "lecture")
public class Lecture {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lectuer_id", nullable = false)
    private Byte id;

    @Column(name = "lecture_name", length = 10)
    private String name;

    @Builder
    public Lecture(String name) {
        this.name = name;
    }

    public static Lecture createLecture(LectureRequest lectureRequest) {
        Lecture lecture = Lecture.builder()
                .name(lectureRequest.getName())
                .build();
        return lecture;
    }
}
