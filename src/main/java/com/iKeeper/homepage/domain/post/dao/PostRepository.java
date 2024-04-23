package com.iKeeper.homepage.domain.post.dao;

import com.iKeeper.homepage.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
