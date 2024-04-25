package com.iKeeper.homepage.domain.post.controller;

import com.iKeeper.homepage.domain.post.dto.PostRequest;
import com.iKeeper.homepage.domain.post.entity.Category;
import com.iKeeper.homepage.domain.post.entity.Post;
import com.iKeeper.homepage.domain.post.service.PostService;
import com.iKeeper.homepage.domain.user.dao.MemberInfo;
import com.iKeeper.homepage.domain.user.dao.MemberRepository;
import com.iKeeper.homepage.domain.user.entity.Member;
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

    @GetMapping(value = "")
    public ResponseEntity postList() {

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.CALENDAR_READ_ALL, postService.searchAllPost()), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity createPost(@RequestHeader("Authorization") String accessToken,
                             @RequestBody @Valid PostRequest postRequest,
                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.AUTH_INVALID_VALUE);
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
                ResponseMessage.CALENDAR_POST, postRequest), HttpStatus.CREATED);
    }
}
