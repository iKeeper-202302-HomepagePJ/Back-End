package com.iKeeper.homepage.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "major")
public class Major implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "major_main", length = 20, nullable = false)
    private String major1;

    @Column(name = "major_double", length = 20, nullable = false)
    private String major2;

    @Column(name = "minor_first", length = 20, nullable = false)
    private String minor1;

    @Column(name = "minor_second", length = 20, nullable = false)
    private String minor2;
}