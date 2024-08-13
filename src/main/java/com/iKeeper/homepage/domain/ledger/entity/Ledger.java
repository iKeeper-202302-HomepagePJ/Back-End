package com.iKeeper.homepage.domain.ledger.entity;

import com.iKeeper.homepage.domain.ledger.dto.LedgerRequest;
import com.iKeeper.homepage.domain.user.entity.Field;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "ledger")
public class Ledger {

    @Id
    @Column(name = "ledger_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ledger_field")
    private Field field;

    @Column(name = "ledger_title")
    private String title;

    @Column(name = "ledger_useday")
    private LocalDate useday;

    @Column(name = "ledger_use")
    private Boolean use;

    @Column(name = "ledger_amount")
    private Long amount;

    @Column(name = "ledger_sum")
    private Long sum;

    @Builder
    public Ledger(Long id, Field field, String title, LocalDate useday,
                  Boolean use, Long amount, Long sum) {

        this.id = id;
        this.field = field;
        this.title = title;
        this.useday = useday;
        this.use = use;
        this.amount = amount;
        this.sum = sum;
    }

    public static Ledger createLedger(Long sum, LedgerRequest ledgerRequest) {

        Ledger ledger = Ledger.builder()
                .id(Long.valueOf(RandomStringUtils.random(10, false, true)))
                .field(ledgerRequest.getField())
                .title(ledgerRequest.getTitle())
                .useday(ledgerRequest.getUseday())
                .use(ledgerRequest.getUse())
                .amount(ledgerRequest.getAmount())
                .sum(sum)
                .build();
        return ledger;
    }
}
