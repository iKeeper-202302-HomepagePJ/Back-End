package com.iKeeper.homepage.domain.post.service;

import com.iKeeper.homepage.domain.post.dao.BookmarkRepository;
import com.iKeeper.homepage.domain.post.dao.HeadlineRepository;
import com.iKeeper.homepage.domain.post.dao.category.CategoryLargeRepository;
import com.iKeeper.homepage.domain.post.dao.category.CategoryRepository;
import com.iKeeper.homepage.domain.post.dao.PostRepository;
import com.iKeeper.homepage.domain.post.dao.category.CategorySmallRepository;
import com.iKeeper.homepage.domain.post.dto.request.FixPostRequest;
import com.iKeeper.homepage.domain.post.dto.response.PostListResponse;
import com.iKeeper.homepage.domain.post.entity.Bookmark;
import com.iKeeper.homepage.domain.post.entity.Headline;
import com.iKeeper.homepage.domain.post.entity.category.Category;
import com.iKeeper.homepage.domain.post.entity.category.CategoryLarge;
import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.domain.post.entity.category.CategorySmall;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryLargeRepository categoryLargeRepository;
    private final CategorySmallRepository categorySmallRepository;
    private final HeadlineRepository headlineRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    public List<PostListResponse> searchAllPost() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponse::new)
                .collect(Collectors.toList());
    }

    public Optional<Post> searchPost(Long id) {

        Optional<Post> searchPost = postRepository.findById(id);
        return searchPost;
    }

    public List<CategoryLarge> categoryLargeList() {
        return categoryLargeRepository.findAll();
    }

    public List<CategorySmall> categorySmallList() {
        return categorySmallRepository.findAll();
    }

    public List<Headline> headlineList() {
        return headlineRepository.findAll();
    }

    public List<Bookmark> bookmarkList() {
        return bookmarkRepository.findAll();
    }

    public Post createPost(Post post, Category category) {

        categoryRepository.save(category);
        return postRepository.save(post);
    }

    public Bookmark createBookmark(Bookmark bookmark) {

        return bookmarkRepository.save(bookmark);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }
}
