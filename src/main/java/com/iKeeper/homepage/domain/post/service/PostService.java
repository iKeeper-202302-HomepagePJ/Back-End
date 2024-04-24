package com.iKeeper.homepage.domain.post.service;

import com.iKeeper.homepage.domain.post.dao.CategoryRepository;
import com.iKeeper.homepage.domain.post.dao.PostRepository;
import com.iKeeper.homepage.domain.post.entity.Category;
import com.iKeeper.homepage.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> searchAllPost() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {

        return postRepository.save(post);
    }
}
