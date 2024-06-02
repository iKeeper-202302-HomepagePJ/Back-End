package com.iKeeper.homepage.domain.post.dto.request;

import com.iKeeper.homepage.domain.post.entity.Headline;
import com.iKeeper.homepage.domain.post.entity.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostRequest {

    //@NotNull(message = "카테고리를 지정해주세요.")
    private Category category;

    //@NotNull(message = "머릿말을 선택해주세요.")
    private Headline headline;

    //@NotBlank(message = "글 제목을 입력해주세요.")
    private String title;

    //@NotBlank(message = "글 내용을 입력해주세요.")
    private String content;

    //@NotNull(message = "글의 공개 여부를 결정해주세요.")
    private Boolean disclosure;

    //@NotNull(message = "댓글의 허용 여부를 결정해주세요.")
    private Boolean commentWhether;

    private List<MultipartFile> files = new ArrayList<>();

    private List<Long> removeFileIds = new ArrayList<>();

    public PostRequest(Category category, Headline headline, String title, String content,
                       Boolean disclosure, Boolean commentWhether, List<MultipartFile> files) {

        this.category = category;
        this.headline = headline;
        this.title = title;
        this.content = content;
        this.disclosure = disclosure;
        this.commentWhether = commentWhether;
        this.files = files;
    }
}
