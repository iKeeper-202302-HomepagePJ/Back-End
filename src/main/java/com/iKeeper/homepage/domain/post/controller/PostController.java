package com.iKeeper.homepage.domain.post.controller;

import com.iKeeper.homepage.domain.file.entity.File;
import com.iKeeper.homepage.domain.file.service.FileService;
import com.iKeeper.homepage.domain.post.dto.request.BookmarkRequest;
import com.iKeeper.homepage.domain.post.dto.request.PostRequest;
import com.iKeeper.homepage.domain.post.dto.response.PostListResponse;
import com.iKeeper.homepage.domain.post.entity.Bookmark;
import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.domain.post.service.PostService;
import com.iKeeper.homepage.domain.user.dto.response.MemberListResponse;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.domain.user.entity.UserRole;
import com.iKeeper.homepage.domain.user.service.UserService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import com.iKeeper.homepage.global.jwt.handler.JwtTokenProvider;
import com.iKeeper.homepage.global.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/posts")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final UserService userService;
    private final PostService postService;
    private final FileService fileService;
    private final FileUtils fileUtils;

    @GetMapping(value = "")
    public ResponseEntity getPostList(@RequestParam(value = "page") int page) {

        Page<PostListResponse> paging = this.postService.getPostList(page);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_ALL, paging), HttpStatus.OK);
    }

    @GetMapping(value = "/{categoryId}")
    public ResponseEntity getPostByCategory(@RequestParam(value = "page") int page,
                                            @PathVariable Long categoryId) {

        Page<PostListResponse> paging = this.postService.getPostByCategory(categoryId, page);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_ALL, paging), HttpStatus.OK);
    }

    @GetMapping(value = "/{categoryId}/{id}")
    public ResponseEntity searchPost(@RequestHeader("Authorization") String accessToken,
                                     @PathVariable Long id, @PathVariable String categoryId) {

        Optional<Post> post = postService.searchPost(id);
        Boolean disclosure = post.get().getDisclosure();

        if (disclosure == Boolean.FALSE) {

            String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
            String postStudentId = post.get().getPostStudentId();

            Optional<MemberListResponse> member = userService.searchMember(studentId);
            UserRole userRole = member.get().getRole();

            if (studentId.equals(postStudentId) || userRole.equals(UserRole.ADMIN)) {
                return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                        ResponseMessage.POST_READ, postService.searchPost(id)), HttpStatus.OK);
            }

            throw new CustomException("error", ErrorCode.POST_SEARCH_FAIL);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ, postService.searchPost(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/category")
    public ResponseEntity getCategoryList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_CATEGORYLARGE, postService.getCategoryList()), HttpStatus.OK);
    }

    @GetMapping(value = "/headline")
    public ResponseEntity headlineList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_HEADLINE, postService.getHeadlineList()), HttpStatus.OK);
    }

    @GetMapping(value = "/bookmark")
    public ResponseEntity bookmarkList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_BOOKMARK, postService.getBookmarkList()), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity createPost(@RequestHeader("Authorization") String accessToken,
                                     @RequestPart("post") @Valid PostRequest postRequest,
                                     @RequestPart(value = "files", required = false) List<MultipartFile> fileList,
                                     BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.POST_INVALID_VALUE);
        }

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
        Optional<MemberListResponse> member = userService.searchMember(studentId);
        String username = member.get().getName();

        Post post = Post.createPost(studentId, username, postRequest);
        Long id = postService.createPost(post);

        List<File> files = fileUtils.uploadFiles(fileList);
        fileService.saveFiles(id, files);

         return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.POST_POST), HttpStatus.CREATED);
    }

    @PostMapping(value = "/bookmark")
    public ResponseEntity createBookmark(@RequestHeader("Authorization") String accessToken,
                                         @RequestBody @Valid BookmarkRequest bookmarkRequest,
                                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.POST_INVALID_VALUE);
        }

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
        Bookmark bookmark = Bookmark.createBookmark(studentId, bookmarkRequest);
        postService.createBookmark(bookmark);

        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.POST_POST_BOOKMARK), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity updatePost(@PathVariable Long id, @RequestBody @Valid PostRequest postRequest,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.POST_INVALID_VALUE);
        }

        postService.updatePost(id, postRequest);

        List<File> uploadFiles = fileUtils.uploadFiles(postRequest.getFiles());
        fileService.saveFiles(id, uploadFiles);
        List<Long> deleteFiles = postRequest.getRemoveFileIds();
        for (Long deleteFile : deleteFiles) {

            Optional<File> deleteFileInfo = fileService.getFileById(deleteFile);
            fileUtils.deleteFiles(deleteFileInfo);
            fileService.deleteFile(deleteFile);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_PATCH), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePost(@RequestHeader("Authorization") String accessToken, @PathVariable Long id) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
        Optional<Post> post = postService.searchPost(id);
        String postStudentId = post.get().getPostStudentId();

        Optional<MemberListResponse> member = userService.searchMember(studentId);
        UserRole userRole = member.get().getRole();

        if (studentId.equals(postStudentId) || userRole.equals(UserRole.ADMIN)) {

            postService.deletePost(id);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_DELETE), HttpStatus.OK);
        }

        throw new CustomException("error", ErrorCode.POST_DELETE_FAIL);
    }

    @DeleteMapping(value = "/bookmark/{id}")
    public ResponseEntity deleteBookmark(@PathVariable Long id) {

        postService.deleteBookmark(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_DELETE_BOOKMARK), HttpStatus.OK);
    }
}
