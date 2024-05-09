package com.iKeeper.homepage.domain.introducion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IntroduceRequest {

    private String content;

    public IntroduceRequest(String content) {

        this.content = content;
    }
}
