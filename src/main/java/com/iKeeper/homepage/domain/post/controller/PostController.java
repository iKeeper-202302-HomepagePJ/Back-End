package com.iKeeper.homepage.domain.post.controller;

import com.iKeeper.homepage.domain.post.dto.PostRequest;
import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.domain.post.service.PostService;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.global.jwt.handler.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/posts")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final PostService postService;

    @PostMapping(value = "")
    public String createPost(@RequestHeader("Authorization") String accessToken,
                             @RequestBody PostRequest postRequest) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken).getName();
        Post post = Post.createPost(studentId, postRequest);
    }
}
