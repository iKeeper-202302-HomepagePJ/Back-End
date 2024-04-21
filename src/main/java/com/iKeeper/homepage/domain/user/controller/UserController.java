package com.iKeeper.homepage.domain.user.controller;

import com.iKeeper.homepage.domain.user.dto.request.UserRequest;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.domain.user.service.MajorService;
import com.iKeeper.homepage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final MajorService majorService;
    private final UserService userService;

    @GetMapping(value = "/major")
    public ResponseEntity majorList() {
        return ResponseEntity.ok(majorService.searchAllMajor());
    }

    @GetMapping(value = "/mypage")
    public ResponseEntity memberInfo(String user) {

        userService.searchMemberInfo(user); // 고쳐야함!!!!!!!
        return ResponseEntity.ok(userService.searchMemberInfo(user));
    }

    @PatchMapping(value = "/{studentId}")
    public String updateUser(@PathVariable String studentId,
                             @RequestBody @Valid UserRequest userRequest,
                             BindingResult bindingResult) {

        userService.updateUser(studentId, userRequest);
        return "redirect:/";
    }

    @DeleteMapping(value = "/{studentId}")
    public ResponseEntity deleteUser(@PathVariable Member studentId) {

        userService.deleteUser(studentId);
        return ResponseEntity.ok().build();
    }
}