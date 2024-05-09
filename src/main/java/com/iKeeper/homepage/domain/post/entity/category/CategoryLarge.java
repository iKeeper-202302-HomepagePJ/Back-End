package com.iKeeper.homepage.domain.post.entity.category;

import com.iKeeper.homepage.domain.post.dto.request.category.CategoryLargeRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "categorylarge")
public class CategoryLarge {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorylarge_id", nullable = false)
    private Byte id;

    @Column(name = "categorylarge_name", length = 20)
    private String name;

    @Builder
    public CategoryLarge(String name) {

        this.name = name;
    }

    public static CategoryLarge createCategoryLarge(CategoryLargeRequest categoryLargeRequest) {
        CategoryLarge categoryLarge = CategoryLarge.builder()
                .name(categoryLargeRequest.getName())
                .build();
        return categoryLarge;
    }
}
