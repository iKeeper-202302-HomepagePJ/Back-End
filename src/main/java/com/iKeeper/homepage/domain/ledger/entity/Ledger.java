package com.iKeeper.homepage.domain.ledger.entity;

import com.iKeeper.homepage.domain.user.entity.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "ledger")
public class Ledger {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ledger_field")
    private Field field;

    @Column(name = "ledger_category")
    private Long category; // 용도가 뭐임?????

    @Column(name = "ledger_title")
    private String title;

    @Column(name = "ledger_useday")
    private LocalDate useday;

    @Column(name = "ledger_plus")
    private Long plus;

    @Column(name = "ledger_minus")
    private Long minus;

    @Column(name = "ledger_sum")
    private Long sum;

    @Column(name = "ledger_evidence")
    private String evidence;
}
