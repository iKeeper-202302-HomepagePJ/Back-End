package com.iKeeper.homepage.domain.post.dto;

import com.iKeeper.homepage.domain.post.entity.Category;
import com.iKeeper.homepage.domain.post.entity.CategoryLarge;
import com.iKeeper.homepage.domain.post.entity.CategorySmall;
import com.iKeeper.homepage.domain.post.entity.Headline;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PostRequest {

    @NotNull(message = "카테고리를 지정해주세요.")
    private Category category;

    @NotNull(message = "머릿말을 선택해주세요.")
    private Headline headline;

    @NotBlank(message = "글 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "글 내용을 입력해주세요.")
    private String content;

    @NotNull(message = "글의 공개 여부를 결정해주세요.")
    private Boolean disclosure;

    @NotNull(message = "댓글의 허용 여부를 결정해주세요.")
    private Boolean commentWhether;

    public PostRequest(Category category, Headline headline, String title,
                       String content, Boolean disclosure, Boolean commentWhether) {

        this.category = category;
        this.headline = headline;
        this.title = title;
        this.content = content;
        this.disclosure = disclosure;
        this.commentWhether = commentWhether;
    }
}
