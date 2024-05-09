package com.iKeeper.homepage.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CommentRequest {

    @NotNull
    private Long post;

    @NotBlank
    private String content;

    public CommentRequest(Long post, String content) {

        this.post = post;
        this.content = content;
    }
}
