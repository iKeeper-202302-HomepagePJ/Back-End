package com.iKeeper.homepage.domain.post.dto.request.category;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategorySmallRequest {

    private String name;

    public CategorySmallRequest(String name) {

        this.name = name;
    }
}