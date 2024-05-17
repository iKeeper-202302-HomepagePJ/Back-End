package com.iKeeper.homepage.domain.ledger.dao;

import com.iKeeper.homepage.domain.ledger.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
}
