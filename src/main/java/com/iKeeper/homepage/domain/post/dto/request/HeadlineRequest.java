package com.iKeeper.homepage.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeadlineRequest {

    private String name;

    public HeadlineRequest(String name) {

        this.name = name;
    }
}
