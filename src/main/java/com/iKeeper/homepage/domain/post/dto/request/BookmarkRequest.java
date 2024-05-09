package com.iKeeper.homepage.domain.post.dto.request;

import com.iKeeper.homepage.domain.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkRequest {

    private String studentId;

    private Post post;

    public BookmarkRequest(String studentId, Post post) {

        this.studentId = studentId;
        this.post = post;
    }
}