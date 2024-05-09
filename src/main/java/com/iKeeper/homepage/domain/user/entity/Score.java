package com.iKeeper.homepage.domain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "score")
public class Score {

    @Id
    @Column(name = "score_id", updatable = false, length = 10, nullable = false)
    private Long id;

    @Column(name = "score_main", nullable = false)
    @ColumnDefault("'0'")
    private Short main;

    @Column(name = "score_field", nullable = false)
    @ColumnDefault("'0'")
    private Short field;

    @Column(name = "score_activity", nullable = false)
    @ColumnDefault("'0'")
    private Short activity;

    @Column(name = "score_etc", nullable = false)
    @ColumnDefault("'0'")
    private Short etc;

    @Column(name = "score_sum", nullable = false)
    @ColumnDefault("'0'")
    private Short sum;
}
