package com.iKeeper.homepage.global.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "field")
public class Field {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id", nullable = false)
    private Byte id;

    @Column(name = "field_name", length = 10)
    private String name;
}