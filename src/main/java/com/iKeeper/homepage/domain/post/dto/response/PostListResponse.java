package com.iKeeper.homepage.domain.post.dto.response;

import com.iKeeper.homepage.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListResponse {

    private Long id;

    private String title;

    private String postUser;

    private LocalDateTime postTime;

    private Boolean disclosure;

    private Boolean fix;

    public PostListResponse(Post post) {

        this.id = post.getId();
        this.title = post.getTitle();
        this.postUser = post.getPostUser();
        this.postTime = post.getPostTime();
        this.disclosure = post.getDisclosure();
        this.fix = post.getFix();
    }
}
