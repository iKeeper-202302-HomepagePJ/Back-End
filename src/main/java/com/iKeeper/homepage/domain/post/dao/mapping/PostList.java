package com.iKeeper.homepage.domain.post.dao.mapping;

import java.time.LocalDateTime;

public interface PostList {

    Long getId();

    String getPostUser();

    String getTitle();

    LocalDateTime getPostTime();

    Boolean getDisclosure();

    Boolean getFix();
}