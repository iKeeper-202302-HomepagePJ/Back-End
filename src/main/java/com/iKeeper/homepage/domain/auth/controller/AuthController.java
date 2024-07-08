package com.iKeeper.homepage.domain.auth.controller;

import com.iKeeper.homepage.domain.auth.dto.request.SignInRequest;
import com.iKeeper.homepage.domain.auth.dto.request.SignUpRequest;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.domain.auth.service.SignInService;
import com.iKeeper.homepage.domain.auth.service.SignUpService;
import com.iKeeper.homepage.domain.user.entity.Score;
import com.iKeeper.homepage.domain.user.service.AdminMemberService;
import com.iKeeper.homepage.domain.user.service.UserService;
import com.iKeeper.homepage.global.error.CustomException;
import com.iKeeper.homepage.global.error.ErrorCode;
import com.iKeeper.homepage.global.httpStatus.DefaultRes;
import com.iKeeper.homepage.global.httpStatus.ResponseMessage;
import com.iKeeper.homepage.global.httpStatus.StatusCode;
import com.iKeeper.homepage.global.jwt.dto.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auths")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final SignUpService signUpService;
    private final SignInService signInService;
    private final AdminMemberService adminMemberService;

    @PostMapping(value = "/login")
    public ResponseEntity signIn(@RequestBody SignInRequest signInRequest) {

        Long studentId = signInRequest.getStudentId();
        String password = signInRequest.getPassword();
        TokenInfo tokenInfo = signInService.login(studentId, password);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK,
                ResponseMessage.AUTH_POST_LOGIN, tokenInfo), HttpStatus.OK);
    }

    @PostMapping(value = "/join")
    public ResponseEntity signUp(@RequestBody @Valid SignUpRequest signUpRequest,
                                 BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new CustomException("일부 입력된 값이 올바르지 않습니다.", ErrorCode.AUTH_INVALID_VALUE);
        }

        Member member = Member.createUser(signUpRequest, passwordEncoder);
        Score score = Score.createScore(signUpRequest.getStudentId());
        signUpService.saveUser(member);
        adminMemberService.createScore(score);
        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED,
                ResponseMessage.AUTH_POST_JOIN), HttpStatus.CREATED);
    }
}