package com.iKeeper.homepage.domain.post.entity;

import com.iKeeper.homepage.domain.file.entity.File;
import com.iKeeper.homepage.domain.post.dto.request.PostRequest;
import com.iKeeper.homepage.domain.post.entity.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "post_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_category")
    private Category category;

    @Column(name = "post_student_id")
    private String postStudentId;

    @Column(name = "post_user")
    private String postUser;

    @ManyToOne
    @JoinColumn(name = "post_headline")
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

    @Column(name = "post_fix", nullable = false)
    private Boolean fix;

     /*@OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<File> files = new ArrayList<>(); */

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id desc")
    private List<Comment> comments = new ArrayList<>();

    public void updateFix(Boolean fix) {
        this.fix = fix;
    }

    public void updatePost(Category category, String content, Boolean disclosure, Headline headline, String title) {

        this.category = category;
        this.content = content;
        this.disclosure = disclosure;
        this.headline = headline;
        this.title = title;
    }

    @Builder
    public Post(Long id, Category category, String postStudentId, String postUser, Headline headline, String title,
                LocalDateTime postTime, String content, Boolean updateCheck, Boolean disclosure,
                Boolean commentWhether, Boolean fix) {

        this.id = id;
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
        this.fix = fix;
    }

    public static Post createPost(String studentId, String username, PostRequest postRequest) {

        Post post = Post.builder()
                .id(Long.valueOf(RandomStringUtils.random(10, false, true)))
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
                .fix(Boolean.FALSE)
                .build();
        return post;
    }
}