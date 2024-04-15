package com.iKeeper.homepage.domain.user.dao;

import com.iKeeper.homepage.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Member, String> {

    Member findAllByStudentId(String studentId);

    Optional<Member> findByStudentId(String username);
}