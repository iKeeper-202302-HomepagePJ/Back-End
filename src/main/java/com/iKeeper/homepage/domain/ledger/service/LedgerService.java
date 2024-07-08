package com.iKeeper.homepage.domain.ledger.service;

import com.iKeeper.homepage.domain.ledger.dao.LedgerRepository;
import com.iKeeper.homepage.domain.ledger.dto.LedgerRequest;
import com.iKeeper.homepage.domain.ledger.entity.Ledger;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    @Transactional
    public Page<Ledger> getLedgerList(int page) {

        Pageable pageable = PageRequest.of(page - 1, 15);
        return ledgerRepository.findAllDesc(pageable);
    }

    public Ledger createLedger(LedgerRequest ledgerRequest) {

        Ledger lastLedger = ledgerRepository.findFirstByOrderByIdDesc();
        Long lastSum = lastLedger.getSum();
        Boolean use = ledgerRequest.getUse();
        Long amount = ledgerRequest.getAmount();

        if (use.equals(Boolean.TRUE)) {

            Long sum = lastSum - amount;
            Ledger ledger = Ledger.createLedger(sum, ledgerRequest);
            return ledgerRepository.save(ledger);
        }

        Long sum = lastSum + amount;
        Ledger ledger = Ledger.createLedger(sum, ledgerRequest);
        return ledgerRepository.save(ledger);
    }

    public void deleteLedger(Long id) {
        ledgerRepository.deleteById(id);
    }
}