package com.iKeeper.homepage.domain.post.dto.request.category;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryLargeRequest {

    private String name;

    public CategoryLargeRequest(String name) {

        this.name = name;
    }
}