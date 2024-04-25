package com.iKeeper.homepage.domain.post.entity;

import com.iKeeper.homepage.domain.post.dto.PostRequest;
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
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_post_id")
    private Post post;

    @Column(name = "comment_student_id")
    private String commentStudentId;

    @Column(name = "comment_username")
    private String commentUser;

    @Column(name = "comment_content")
    private String content;

    @UpdateTimestamp
    @Column(name = "comment_timestamp")
    private LocalDateTime commentTime;
}
