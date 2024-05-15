package com.iKeeper.homepage.domain.user.dao;

import com.iKeeper.homepage.domain.user.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
