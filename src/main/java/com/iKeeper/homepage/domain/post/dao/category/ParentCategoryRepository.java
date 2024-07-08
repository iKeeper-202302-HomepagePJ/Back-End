package com.iKeeper.homepage.domain.post.dao.category;

import com.iKeeper.homepage.domain.post.entity.category.ParentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentCategoryRepository extends JpaRepository<ParentCategory, Long> {
}
