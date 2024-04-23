package com.iKeeper.homepage.domain.post.entity;

import com.iKeeper.homepage.domain.post.dao.PostUserInfo;
import com.iKeeper.homepage.domain.post.dto.PostRequest;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.global.jwt.handler.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @JoinColumn(name = "post_category", referencedColumnName = "category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "post_user")
    private Optional<PostUserInfo> postUser;

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
    public Post(Category category, Member postUser, Headline headline, String title, LocalDateTime postTime,
                String content, Boolean updateCheck, Boolean disclosure, Boolean commentWhether) {

        this.category = category;
        this.postUser = postUser;
        this.headline = headline;
        this.title = title;
        this.postTime = postTime;
        this.content = content;
        this.updateCheck = updateCheck;
        this.disclosure = disclosure;
        this.commentWhether = commentWhether;
    }

    public static Post createPost(Member studentId, PostRequest postRequest) {

        Post post = Post.builder()
                .category(postRequest.getCategory())
                .postUser(studentId)
                .build();
        return post;
    }
}