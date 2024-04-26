package com.iKeeper.homepage.domain.post.service;

import com.iKeeper.homepage.domain.post.dao.CommentRepository;
import com.iKeeper.homepage.domain.post.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment createComment(Comment comment) {

        return commentRepository.save(comment);
    }
}
