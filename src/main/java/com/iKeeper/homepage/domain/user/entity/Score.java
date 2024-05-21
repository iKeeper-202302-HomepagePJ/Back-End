package com.iKeeper.homepage.domain.user.entity;

import com.iKeeper.homepage.domain.auth.dto.request.SignUpRequest;
import com.iKeeper.homepage.domain.user.dto.request.ScoreRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "score")
public class Score {

    @Id
    @Column(name = "score_id")
    private String id;

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

    public void updateScore(Short main, Short field, Short activity, Short etc, Short sum) {

        this.main = main;
        this.field = field;
        this.activity = activity;
        this.etc = etc;
        this.sum = sum;
    }

    @Builder
    public Score(String id, Short main, Short field, Short activity, Short etc, Short sum) {

        this.id = id;
        this.main = main;
        this.field = field;
        this.activity = activity;
        this.etc = etc;
        this.sum = sum;
    }

    public static Score createScore(String studentId) {

        Score score = Score.builder()
                .id(studentId)
                .main((short) 0)
                .field((short) 0)
                .activity((short) 0)
                .etc((short) 0)
                .sum((short) 0)
                .build();
        return score;
    }
}