package com.iKeeper.homepage.domain.post.entity;

import com.iKeeper.homepage.domain.user.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

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

    //@OneToOne
    //@JoinColumn(name = "category_id")
    //private Category category;

    @Column(name = "post_title", length = 50, nullable = false)
    private String title;

    //@UpdateTimestamp
    //@Column(name = "post_timestamp")
    //private String timestamp;

    //@UpdateTimestamp
    //@Column(name = "post_rewrite_timestamp")
    //private String reTimestamp;

    @Column(name = "post_content", length = 30, nullable = false)
    private String content;

    @Column(name = "post_disclosure", nullable = false)
    private Boolean disclosure;

    @Column(name = "post_comment_whether", length = 30, nullable = false)
    private Boolean commentWhether;
}