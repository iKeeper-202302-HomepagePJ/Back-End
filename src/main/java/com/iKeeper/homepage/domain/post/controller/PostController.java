package com.iKeeper.homepage.domain.post.controller;

import com.iKeeper.homepage.domain.post.dto.request.BookmarkRequest;
import com.iKeeper.homepage.domain.post.dto.request.PostRequest;
import com.iKeeper.homepage.domain.post.entity.Bookmark;
import com.iKeeper.homepage.domain.post.entity.category.Category;
import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.domain.post.service.PostService;
import com.iKeeper.homepage.domain.user.dao.mapping.MemberInfo;
import com.iKeeper.homepage.domain.user.service.UserService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import com.iKeeper.homepage.global.jwt.handler.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/posts")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final UserService userService;
    private final PostService postService;

    @GetMapping(value = "")
    public ResponseEntity searchAllPost() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_ALL, postService.searchAllPost()), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity searchPost(@RequestHeader("Authorization") String accessToken,
                                     @RequestParam(value = "id", required = false) Long id) {

        Optional<Post> post = postService.searchPost(id);
        Boolean disclosure = post.get().getDisclosure();

        if (disclosure == Boolean.FALSE) {

            String userRole = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getAuthorities().toString();

            if(userRole.equals("USER_ADMIN")) {
                return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                        ResponseMessage.POST_READ_ALL, postService.searchPost(id)), HttpStatus.OK);
            } // 해당 부분 수정 필요 (권한 가져오는 방식)

            throw new CustomException("error", ErrorCode.POST_INVALID_VALUE);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_ALL, postService.searchPost(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/categorylarge")
    public ResponseEntity categoryLargeList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_ALL, postService.categoryLargeList()), HttpStatus.OK);
    }

    @GetMapping(value = "/categorysmall")
    public ResponseEntity categorySmallList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_ALL, postService.categorySmallList()), HttpStatus.OK);
    }

    @GetMapping(value = "/headline")
    public ResponseEntity headlineList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_ALL, postService.headlineList()), HttpStatus.OK);
    }

    @GetMapping(value = "/bookmark")
    public ResponseEntity bookmarkList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_READ_ALL, postService.bookmarkList()), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity createPost(@RequestHeader("Authorization") String accessToken,
                             @RequestBody @Valid PostRequest postRequest,
                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.POST_INVALID_VALUE);
        }

        else {

            String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
            Optional<MemberInfo> member = userService.searchMemberInfo(studentId);
            String username = member.get().getName();

            Post post = Post.createPost(studentId, username, postRequest);
            Category category = Category.createCategory(postRequest);
            postService.createPost(post, category);
        }

         return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.POST_POST, postRequest), HttpStatus.CREATED);
    }

    @PostMapping(value = "/bookmark")
    public ResponseEntity createBookmark(@RequestHeader("Authorization") String accessToken,
                                         @RequestBody @Valid BookmarkRequest bookmarkRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.POST_INVALID_VALUE);
        }

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
        Bookmark bookmark = Bookmark.createBookmark(studentId, bookmarkRequest);
        postService.createBookmark(bookmark);

        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.POST_POST, bookmarkRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePost(@RequestHeader("Authorization") String accessToken, @PathVariable Long id) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
        Optional<Post> post = postService.searchPost(id);
        String postStudentId = post.get().getPostStudentId();

        if (studentId.equals(postStudentId)) {

            postService.deletePost(id);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CALENDAR_DELETE), HttpStatus.OK);
        }

        throw new CustomException("error", ErrorCode.POST_INVALID_VALUE);
    }

    @DeleteMapping(value = "/bookmark/{id}")
    public ResponseEntity deleteBookmark(@PathVariable Long id) {

        postService.deleteBookmark(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CALENDAR_DELETE), HttpStatus.OK);
    }
}
