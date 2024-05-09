package com.iKeeper.homepage.domain.introducion.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HistoryRequest {

    private String year;

    private String content;

    public HistoryRequest(String year, String content) {

        this.year = year;
        this.content = content;
    }
}