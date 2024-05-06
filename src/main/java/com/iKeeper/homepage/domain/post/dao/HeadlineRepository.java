package com.iKeeper.homepage.domain.post.dao;

import com.iKeeper.homepage.domain.post.entity.Headline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadlineRepository extends JpaRepository<Headline, Long> {
}
