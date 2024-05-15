package com.iKeeper.homepage.domain.post.service;

import com.iKeeper.homepage.domain.post.dao.CommentRepository;
import com.iKeeper.homepage.domain.post.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Optional<Comment> searchComment(Long id) {

        Optional<Comment> searchComment = commentRepository.findById(id);
        return searchComment;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
