package com.iKeeper.homepage.domain.attendance.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lectuer_id", nullable = false)
    private Byte id;

    @Column(name = "lecture_name", length = 10)
    private String name;
}
