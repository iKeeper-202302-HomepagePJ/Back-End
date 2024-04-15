package com.iKeeper.homepage.domain.auth.controller;

import com.iKeeper.homepage.domain.auth.dto.request.SignInRequest;
import com.iKeeper.homepage.domain.auth.dto.request.SignUpRequest;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.domain.auth.service.SignInService;
import com.iKeeper.homepage.domain.auth.service.SignUpService;
import com.iKeeper.homepage.global.jwt.dto.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(value = "/login")
    public TokenInfo signIn(@RequestBody SignInRequest signInRequest) {

        Long studentId = signInRequest.getStudentId();
        String password = signInRequest.getPassword();
        TokenInfo tokenInfo = signInService.login(studentId, password);
        return tokenInfo;
    }

    @PostMapping(value = "/join")
    public String signUp(@RequestBody @Valid SignUpRequest signUpRequest,
                         BindingResult bindingResult, Model model) {

        try {
            Member user = Member.createUser(signUpRequest, passwordEncoder);
            signUpService.saveUser(user);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/error"; // 회원가입 페이지로 리다이렉션
        }

        return "/success"; // 회원가입 페이지로 리다이렉션
    }
}