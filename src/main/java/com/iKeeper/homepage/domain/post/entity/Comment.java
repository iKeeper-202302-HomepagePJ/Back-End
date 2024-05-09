package com.iKeeper.homepage.domain.post.entity;

import com.iKeeper.homepage.domain.post.dto.request.CommentRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "comment_post_id")
    private Long post;

    @Column(name = "comment_student_id")
    private String commentStudentId;

    @Column(name = "comment_username")
    private String commentUser;

    @Column(name = "comment_content")
    private String content;

    @UpdateTimestamp
    @Column(name = "comment_timestamp")
    private LocalDateTime commentTime;

    @Builder
    public Comment(Long post, String commentStudentId, String commentUser,
                   String content, LocalDateTime commentTime) {

        this.post = post;
        this.commentStudentId = commentStudentId;
        this.commentUser = commentUser;
        this.content = content;
        this.commentTime = commentTime;
    }

    public static Comment createComment(String studentId, String username, CommentRequest commentRequest) {

        Comment comment = Comment.builder()
                .post(commentRequest.getPost())
                .commentStudentId(studentId)
                .commentUser(username)
                .content(commentRequest.getContent())
                .commentTime(LocalDateTime.now())
                .build();
        return comment;
    }
}
