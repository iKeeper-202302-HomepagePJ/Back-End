package com.iKeeper.homepage.domain.ledger.dao;

import com.iKeeper.homepage.domain.ledger.entity.Ledger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {

    Ledger findFirstByOrderByIdDesc();

    @Query("SELECT p FROM Post p "
            + "ORDER BY p.postTime DESC")
    Page<Ledger> findAllDesc(Pageable pageable);
}
