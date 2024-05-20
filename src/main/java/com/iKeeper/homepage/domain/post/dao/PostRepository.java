package com.iKeeper.homepage.domain.post.dao;

import com.iKeeper.homepage.domain.post.dao.mapping.PostList;
import com.iKeeper.homepage.domain.post.dto.response.PostListResponse;
import com.iKeeper.homepage.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<PostList> findByPostStudentId(String studentId);

    Page<Post> findAll(Pageable pageable);
}