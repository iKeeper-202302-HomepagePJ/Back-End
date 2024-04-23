package com.iKeeper.homepage.domain.post.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "categorysmall")
public class CategorySmall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorysmall_id", nullable = false)
    private Byte id;

    @Column(name = "categorysmall_name", length = 20)
    private String name;
}
