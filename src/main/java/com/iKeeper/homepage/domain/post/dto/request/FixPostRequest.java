package com.iKeeper.homepage.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class FixPostRequest {

    @NotNull
    private Boolean fix;

    public FixPostRequest(Boolean fix) {

        this.fix = fix;
    }
}