package com.iKeeper.homepage.domain.user.dao;

import com.iKeeper.homepage.domain.user.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {

}
