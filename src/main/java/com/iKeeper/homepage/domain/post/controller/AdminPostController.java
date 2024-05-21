package com.iKeeper.homepage.domain.post.controller;

import com.iKeeper.homepage.domain.post.dto.request.category.CategoryRequest;
import com.iKeeper.homepage.domain.post.dto.request.FixPostRequest;
import com.iKeeper.homepage.domain.post.dto.request.HeadlineRequest;
import com.iKeeper.homepage.domain.post.entity.Headline;
import com.iKeeper.homepage.domain.post.entity.category.Category;
import com.iKeeper.homepage.domain.post.service.AdminPostService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/posts")
@RequiredArgsConstructor
public class AdminPostController {

    private final AdminPostService adminPostService;

    @PostMapping(value = "/category")
    public ResponseEntity createCategory(@RequestBody @Valid CategoryRequest categoryRequest,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("일부 입력된 값이 올바르지 않습니다.", ErrorCode.POST_INVALID_VALUE);
        }

        Category category = Category.createCategory(categoryRequest);
        adminPostService.createCategory(category);

        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.POST_POST_CATEGORYSMALL), HttpStatus.CREATED);
    }

    @PostMapping(value = "/headline")
    public ResponseEntity createHeadline(@RequestBody @Valid HeadlineRequest headlineRequest,
                                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException("일부 입력된 값이 올바르지 않습니다.", ErrorCode.CALENDAR_INVALID_VALUE);
        }

        Headline headline = Headline.createHeadline(headlineRequest);
        adminPostService.createHeadline(headline);

        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.POST_POST_HEADLINE), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/fix/{id}")
    public ResponseEntity fixPost(@PathVariable Long id, @RequestBody @Valid FixPostRequest fixPostRequest,
                                  BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomException("error", ErrorCode.POST_INVALID_VALUE);
        }

        adminPostService.fixPost(id, fixPostRequest);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.POST_PATCH), HttpStatus.OK);
    }

    /* @DeleteMapping(value = "/categorylarge/{id}")
    public ResponseEntity deleteCategoryLarge(@PathVariable Long id) {

        adminPostService.deleteCategoryLarge(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_DELETE_CATEGORYLARGE), HttpStatus.OK);
    } */

    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity deleteCategorySmall(@PathVariable Long id) {

        adminPostService.deleteCategory(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_DELETE_CATEGORYSMALL), HttpStatus.OK);
    }

    @DeleteMapping(value = "/headline/{id}")
    public ResponseEntity deleteHeadline(@PathVariable Long id) {

        adminPostService.deleteHeadline(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_DELETE_HEADLINE), HttpStatus.OK);
    }
}
