package com.iKeeper.homepage.domain.ledger.dto;

import com.iKeeper.homepage.domain.user.entity.Field;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class LedgerRequest {

    private Long id;

    private Field field;

    private String title;

    private LocalDate useday;

    private Boolean use;

    private Long amount;

    private String evidence;

    public LedgerRequest(Field field, String title, LocalDate useday,
                         Boolean use, Long amount, String evidence) {

        this.field = field;
        this.title = title;
        this.useday = useday;
        this.use = use;
        this.amount = amount;
        this.evidence = evidence;
    }
}
