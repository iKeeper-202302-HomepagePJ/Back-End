package com.iKeeper.homepage.domain.ledger.service;

import com.iKeeper.homepage.domain.ledger.dao.LedgerRepository;
import com.iKeeper.homepage.domain.ledger.dto.LedgerRequest;
import com.iKeeper.homepage.domain.ledger.entity.Ledger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    public List<Ledger> ledgerList() {
        return ledgerRepository.findAllByOrderByIdDesc();
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