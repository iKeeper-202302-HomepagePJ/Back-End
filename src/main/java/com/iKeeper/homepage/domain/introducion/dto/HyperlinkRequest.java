package com.iKeeper.homepage.domain.introducion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HyperlinkRequest {

    private String name;

    private String img;

    private String url;

    public HyperlinkRequest(String name, String img, String url) {

        this.name = name;
        this.img = img;
        this.url = url;
    }
}
