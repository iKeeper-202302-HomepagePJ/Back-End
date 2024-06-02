package com.iKeeper.homepage.domain.file.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "postfile")
public class File {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postfile_id")
    private Long id;

    @Column(name = "postfile_post_id")
    private Long postId;

    @Column(name = "postfile_name")
    private String name;

    @Column(name = "postfile_save_name")
    private String saveName;

    @Column(name = "postfile_size")
    private Long size;

    @Column(name = "postfile_delete_boolean")
    private Boolean delete;

    @Column(name = "postfile_timestamp")
    private LocalDateTime timestamp;

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Builder
    public File(String name, String saveName, Long size, Boolean delete,
                LocalDateTime timestamp) {
        this.name = name;
        this.saveName = saveName;
        this.size = size;
        this.delete = delete;
        this.timestamp = timestamp;
    }
}