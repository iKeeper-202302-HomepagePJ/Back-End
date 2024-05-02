package com.iKeeper.homepage.domain.introducion.dao;

import com.iKeeper.homepage.domain.introducion.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
