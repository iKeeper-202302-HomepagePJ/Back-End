package com.iKeeper.homepage.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MajorRequest {

    private String name;

    public MajorRequest(String name) {

        this.name = name;
    }
}
