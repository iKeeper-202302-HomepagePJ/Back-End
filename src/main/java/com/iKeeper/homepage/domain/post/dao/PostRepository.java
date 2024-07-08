package com.iKeeper.homepage.domain.post.dao;

import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.domain.post.entity.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p "
        + "ORDER BY p.fix DESC, p.postTime DESC")
    Page<Post> findAllDesc(Pageable pageable);

    @Query("SELECT p FROM Post p where p.category.id = :categoryId ORDER by p.fix DESC, p.postTime DESC")
    Page<Post> findAllByCategory_Id(Long categoryId, Pageable pageable);

    @Query("SELECT p FROM Post p "
            + "ORDER BY p.postTime DESC")
    Page<Post> findByPostStudentId(String studentId, Pageable pageable);
}