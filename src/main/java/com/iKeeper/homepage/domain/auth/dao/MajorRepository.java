package com.iKeeper.homepage.domain.auth.dao;

import com.iKeeper.homepage.domain.auth.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
}
