package com.iKeeper.homepage.domain.user.dao;

import com.iKeeper.homepage.domain.user.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
