package com.iKeeper.homepage.domain.post.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "headline")
public class Headline {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "headline_id", nullable = false)
    private Byte id;

    @Column(name = "headline_name", length = 10)
    private String name;
}