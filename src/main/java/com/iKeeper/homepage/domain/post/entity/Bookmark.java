package com.iKeeper.homepage.domain.post.entity;

import com.iKeeper.homepage.domain.post.dto.request.BookmarkRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "bookmark")
public class Bookmark {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id", nullable = false)
    private Long id;

    @Column(name = "bookmark_student_id", length = 10, nullable = false)
    private String studentId;

    @ManyToOne
    @JoinColumn(name = "bookmark_post_id")
    private Post post;

    @Builder
    public Bookmark(String studentId, Post post) {

        this.studentId = studentId;
        this.post = post;
    }

    public static Bookmark createBookmark(String studentId, BookmarkRequest bookmarkRequest) {
        Bookmark bookmark = Bookmark.builder()
                .studentId(studentId)
                .post(bookmarkRequest.getPost())
                .build();
        return bookmark;
    }
}
