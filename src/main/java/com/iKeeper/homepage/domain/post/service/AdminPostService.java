package com.iKeeper.homepage.domain.post.service;

import com.iKeeper.homepage.domain.post.dao.HeadlineRepository;
import com.iKeeper.homepage.domain.post.dao.PostRepository;
import com.iKeeper.homepage.domain.post.dao.category.CategoryLargeRepository;
import com.iKeeper.homepage.domain.post.dao.category.CategorySmallRepository;
import com.iKeeper.homepage.domain.post.dto.request.FixPostRequest;
import com.iKeeper.homepage.domain.post.entity.Headline;
import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.domain.post.entity.category.CategoryLarge;
import com.iKeeper.homepage.domain.post.entity.category.CategorySmall;
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
    private final CategorySmallRepository categorySmallRepository;
    private final HeadlineRepository headlineRepository;

    /* public CategoryLarge createCategoryLarge(CategoryLarge categoryLarge) {
        return categoryLargeRepository.save(categoryLarge);
    } */

    public CategorySmall createCategorySmall(CategorySmall categorySmall) {
        return categorySmallRepository.save(categorySmall);
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

    /* public void deleteCategoryLarge(Long id) {
        categoryLargeRepository.deleteById(id);
    } */

    public void deleteCategorySmall(Long id) {
        categorySmallRepository.deleteById(id);
    }

    public void deleteHeadline(Long id) {
        headlineRepository.deleteById(id);
    }
}
