package com.iKeeper.homepage.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScoreRequest {

    private String id;

    private Short main;

    private Short field;

    private Short activity;

    private Short etc;

    private Short sum;

    public ScoreRequest(String id, Short main, Short field, Short activity, Short etc, Short sum) {

        this.id = id;
        this.main = main;
        this.field = field;
        this.activity = activity;
        this.etc = etc;
        this.sum = sum;
    }
}
