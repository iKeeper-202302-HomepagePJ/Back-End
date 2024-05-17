package com.iKeeper.homepage.domain.post.service;

import com.iKeeper.homepage.domain.post.dao.BookmarkRepository;
import com.iKeeper.homepage.domain.post.dao.HeadlineRepository;
import com.iKeeper.homepage.domain.post.dao.category.CategoryLargeRepository;
import com.iKeeper.homepage.domain.post.dao.category.CategoryRepository;
import com.iKeeper.homepage.domain.post.dao.PostRepository;
import com.iKeeper.homepage.domain.post.dao.category.CategorySmallRepository;
import com.iKeeper.homepage.domain.post.dto.request.FixPostRequest;
import com.iKeeper.homepage.domain.post.dto.request.PostRequest;
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

import javax.transaction.Transactional;
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

    @Transactional
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

    @Transactional
    public Optional<Post> updatePost(Long id, PostRequest postRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException("해당 ID의 글이 존재하지 않습니다.", ErrorCode.POST_INVALID_VALUE));

        post.updatePost(postRequest.getCategory(), postRequest.getContent(), postRequest.getDisclosure(),
                postRequest.getHeadline(), postRequest.getTitle());
        return postRepository.findById(id);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }
}
