package com.iKeeper.homepage.domain.post.service;

import com.iKeeper.homepage.domain.post.dao.BookmarkRepository;
import com.iKeeper.homepage.domain.post.dao.HeadlineRepository;
import com.iKeeper.homepage.domain.post.dao.PostRepository;
import com.iKeeper.homepage.domain.post.dao.category.ParentCategoryRepository;
import com.iKeeper.homepage.domain.post.dto.request.PostRequest;
import com.iKeeper.homepage.domain.post.dto.response.PostListResponse;
import com.iKeeper.homepage.domain.post.entity.Bookmark;
import com.iKeeper.homepage.domain.post.entity.Headline;
import com.iKeeper.homepage.domain.post.entity.category.Category;
import com.iKeeper.homepage.domain.post.entity.category.ParentCategory;
import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ParentCategoryRepository parentCategoryRepository;
    private final HeadlineRepository headlineRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public Page<PostListResponse> getPostList(int page) {

        Pageable pageable = PageRequest.of(page - 1, 15);
        return postRepository.findAllDesc(pageable)
                .map(PostListResponse::new);
    }

    @Transactional
    public Page<PostListResponse> getPostByCategory(Long categoryId, int page) {

        Pageable pageable = PageRequest.of(page - 1, 15);
        return postRepository.findAllByCategory_Id(categoryId, pageable)
                .map(PostListResponse::new);
    }

    public Optional<Post> searchPost(Long id) {

        Optional<Post> searchPost = postRepository.findById(id);
        return searchPost;
    }

    public List<ParentCategory> getCategoryList() {
        return parentCategoryRepository.findAll();
    }

    public List<Headline> getHeadlineList() {
        return headlineRepository.findAll();
    }

    public List<Bookmark> getBookmarkList() {
        return bookmarkRepository.findAll();
    }

    public Long createPost(Post post) {

        postRepository.save(post);
        return post.getId();
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
