package com.iKeeper.homepage.domain.post.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "categorylarge")
public class CategoryLarge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorylarge_id", nullable = false)
    private Byte id;

    @Column(name = "categorylarge_name", length = 20)
    private String name;
}
