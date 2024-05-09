package com.iKeeper.homepage.domain.introducion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HyperlinkRequest {

    private String img;

    private String url;

    public HyperlinkRequest(String img, String url) {

        this.img = img;
        this.url = url;
    }
}
