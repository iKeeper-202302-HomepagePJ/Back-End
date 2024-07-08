package com.iKeeper.homepage.domain.post.entity.category;

import com.iKeeper.homepage.domain.post.dto.request.category.CategoryRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "category")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column(name = "category_parent")
    private Long parent;

    @Column(name = "category_name", length = 20)
    private String name;

    @Builder
    public Category(Long parent, String name) {

        this.parent = parent;
        this.name = name;
    }

    public static Category createCategory(CategoryRequest categoryRequest) {
        Category categorySmall = Category.builder()
                .parent(categoryRequest.getParent())
                .name(categoryRequest.getName())
                .build();
        return categorySmall;
    }
}
