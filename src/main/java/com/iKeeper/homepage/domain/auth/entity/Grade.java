package com.iKeeper.homepage.domain.auth.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id", nullable = false)
    private Long id;

    @Column(name = "grade_name", length = 10, nullable = false)
    @ColumnDefault("'1학년 1학차'")
    private String name;
}