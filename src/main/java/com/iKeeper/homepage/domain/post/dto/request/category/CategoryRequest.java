package com.iKeeper.homepage.domain.post.dto.request.category;

import com.iKeeper.homepage.domain.post.entity.category.ParentCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryRequest {

    private Long parent;

    private String name;

    public CategoryRequest(Long parent, String name) {

        this.parent = parent;
        this.name = name;
    }
}