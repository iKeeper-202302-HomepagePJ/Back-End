package com.iKeeper.homepage.domain.post.entity.category;

import com.iKeeper.homepage.domain.post.dto.request.category.CategorySmallRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "categorysmall")
public class CategorySmall {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categorysmall_id", nullable = false)
    private Byte id;

    @Column(name = "categorysmall_name", length = 20)
    private String name;

    @Builder
    public CategorySmall(String name) {

        this.name = name;
    }

    public static CategorySmall createCategorySmall(CategorySmallRequest categorySmallRequest) {
        CategorySmall categorySmall = CategorySmall.builder()
                .name(categorySmallRequest.getName())
                .build();
        return categorySmall;
    }
}
