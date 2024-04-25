package com.iKeeper.homepage.domain.post.dao;

import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.domain.user.dao.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByPostStudentId(String studentId);
}
