package com.iKeeper.homepage.domain.user.dao;

import com.iKeeper.homepage.domain.user.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, String > {
}
