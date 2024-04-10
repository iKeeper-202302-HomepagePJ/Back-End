package com.iKeeper.homepage.domain.auth.dao;

import com.iKeeper.homepage.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findAllByStudent(String student);
    Optional<User> findByStudent(String username);
}