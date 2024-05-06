package com.iKeeper.homepage.domain.post.dao;

import com.iKeeper.homepage.domain.post.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
