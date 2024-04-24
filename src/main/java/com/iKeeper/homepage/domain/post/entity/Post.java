package com.iKeeper.homepage.domain.post.entity;

import com.iKeeper.homepage.domain.post.dto.PostRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "post_category")
    private Category category;

    @Column(name = "post_student_id")
    private String postStudentId;

    @Column(name = "post_user")
    private String postUser;

    @OneToOne
    @JoinColumn(name = "post_headline", referencedColumnName = "headline_id")
    private Headline headline;

    @Column(name = "post_title", length = 50, nullable = false)
    private String title;

    @UpdateTimestamp
    @Column(name = "post_timestamp")
    private LocalDateTime postTime;

    @Column(name = "post_content", nullable = false)
    private String content;

    @Column(name = "post_update_check")
    private Boolean updateCheck;

    @Column(name = "post_disclosure", nullable = false)
    private Boolean disclosure;

    @Column(name = "post_comment_whether", nullable = false)
    private Boolean commentWhether;

    @Builder
    public Post(Category category, String postStudentId, String postUser, Headline headline, String title,
                LocalDateTime postTime, String content, Boolean updateCheck, Boolean disclosure, Boolean commentWhether) {

        this.category = category;
        this.postStudentId = postStudentId;
        this.postUser = postUser;
        this.headline = headline;
        this.title = title;
        this.postTime = postTime;
        this.content = content;
        this.updateCheck = updateCheck;
        this.disclosure = disclosure;
        this.commentWhether = commentWhether;
    }

    public static Post createPost(String studentId, String username, PostRequest postRequest) {

        Post post = Post.builder()
                .category(postRequest.getCategory())
                .postStudentId(studentId)
                .postUser(username)
                .headline(postRequest.getHeadline())
                .title(postRequest.getTitle())
                .postTime(LocalDateTime.now())
                .content(postRequest.getContent())
                .updateCheck(Boolean.FALSE)
                .disclosure(postRequest.getDisclosure())
                .commentWhether(postRequest.getCommentWhether())
                .build();
        return post;
    }
}