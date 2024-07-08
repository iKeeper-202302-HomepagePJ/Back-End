package com.iKeeper.homepage.domain.file.dao;

import com.iKeeper.homepage.domain.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findAllByPostId(Long postId);
}