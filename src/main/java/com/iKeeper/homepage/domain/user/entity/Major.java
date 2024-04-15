package com.iKeeper.homepage.domain.user.entity;

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
}