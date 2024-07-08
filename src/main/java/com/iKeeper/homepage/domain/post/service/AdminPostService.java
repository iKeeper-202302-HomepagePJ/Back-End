package com.iKeeper.homepage.domain.post.service;

import com.iKeeper.homepage.domain.post.dao.HeadlineRepository;
import com.iKeeper.homepage.domain.post.dao.PostRepository;
import com.iKeeper.homepage.domain.post.dao.category.CategoryRepository;
import com.iKeeper.homepage.domain.post.dto.request.FixPostRequest;
import com.iKeeper.homepage.domain.post.entity.Headline;
import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.domain.post.entity.category.Category;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminPostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final HeadlineRepository headlineRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Headline createHeadline(Headline headline) {
        return headlineRepository.save(headline);
    }

    @Transactional
    public Optional<Post> fixPost(Long id, FixPostRequest fixPostRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException("해당 ID의 글이 존재하지 않습니다.", ErrorCode.CALENDAR_NOT_FOUND));

        post.updateFix(fixPostRequest.getFix());
        return postRepository.findById(id);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteHeadline(Long id) {
        headlineRepository.deleteById(id);
    }
}
