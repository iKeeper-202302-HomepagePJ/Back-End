package com.iKeeper.homepage.domain.attendance.dao;

import com.iKeeper.homepage.domain.attendance.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
