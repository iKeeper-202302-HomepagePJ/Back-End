package com.iKeeper.homepage.domain.auth.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "major_name", length = 20, nullable = false)
    @ColumnDefault("'없음'")
    private String name;
}