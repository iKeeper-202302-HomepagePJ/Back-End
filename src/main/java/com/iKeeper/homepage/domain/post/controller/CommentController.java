package com.iKeeper.homepage.domain.post.controller;

import com.iKeeper.homepage.domain.post.dto.request.CommentRequest;
import com.iKeeper.homepage.domain.post.entity.Comment;
import com.iKeeper.homepage.domain.post.service.CommentService;
import com.iKeeper.homepage.domain.user.dao.mapping.MemberList;
import com.iKeeper.homepage.domain.user.dto.response.MemberListResponse;
import com.iKeeper.homepage.domain.user.entity.UserRole;
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
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/posts")
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final CommentService commentService;

    @PostMapping(value = "/comment")
    public ResponseEntity createComment(@RequestHeader("Authorization") String accessToken,
                                        @RequestBody @Valid CommentRequest commentRequest,
                                        BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.POST_INVALID_VALUE);
        }

        else {

            String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
            Optional<MemberList> member = userService.searchMember(studentId);
            String username = member.get().getName();

            Comment comment = Comment.createComment(studentId, username, commentRequest);
            commentService.createComment(comment);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.POST_POST_COMMENT, commentRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/comment/{id}")
    public ResponseEntity deleteComment(@RequestHeader("Authorization") String accessToken, @PathVariable Long id) {

        String studentId = jwtTokenProvider.getAuthentication(accessToken.substring(7)).getName();
        Optional<Comment> comment = commentService.searchComment(id);
        String commentStudentId = comment.get().getCommentStudentId();

        Optional<MemberList> member = userService.searchMember(studentId);
        String userRole = member.get().getRole();

        if (studentId.equals(commentStudentId) || userRole.equals("ADMIN")) {

            commentService.deleteComment(id);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_DELETE_COMMENT), HttpStatus.OK);
        }

        throw new CustomException("error", ErrorCode.POST_DELETE_FAIL);
    }
}

