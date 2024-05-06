package com.iKeeper.homepage.domain.post.dao.mapping;

import com.iKeeper.homepage.domain.user.entity.Grade;
import com.iKeeper.homepage.domain.user.entity.Major;
import com.iKeeper.homepage.domain.user.entity.Status;
import com.iKeeper.homepage.global.entity.Field;

import java.time.LocalDateTime;

public interface PostList {

    Long getId();

    String getPostUser();

    String getTitle();

    LocalDateTime getPostTime();

    Boolean getDisclosure();

    Boolean getFix();
}