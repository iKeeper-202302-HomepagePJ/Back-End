package com.iKeeper.homepage.domain.auth.controller;

import com.iKeeper.homepage.domain.auth.dto.request.UserSignInRequest;
import com.iKeeper.homepage.domain.auth.dto.request.UserSignUpRequest;
import com.iKeeper.homepage.domain.auth.entity.User;
import com.iKeeper.homepage.domain.auth.service.UserSignInService;
import com.iKeeper.homepage.domain.auth.service.UserSignUpService;
import com.iKeeper.homepage.global.jwt.dto.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController {

    private PasswordEncoder passwordEncoder;
    private final UserSignUpService userSignUpService;
    private final UserSignInService userSignInService;

    @PostMapping(value = "/login")
    public TokenInfo signIn(@RequestBody UserSignInRequest userSignInRequest) {

        String student = userSignInRequest.getStudent();
        String password = userSignInRequest.getPassword();
        TokenInfo tokenInfo = userSignInService.login(student, password);
        return tokenInfo;
    }

    @PostMapping(value = "/join")
    public String signUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/asdf"; // 회원가입 페이지로 리다이렉션
        }

        try {
            User user = User.createUser(userSignUpRequest, passwordEncoder);
            userSignUpService.saveUser(user);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/asdf"; // 회원가입 페이지로 리다이렉션
        }

        return "redirect:/"; // 회원가입 페이지로 리다이렉션
    }
}