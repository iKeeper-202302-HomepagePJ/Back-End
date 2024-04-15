package com.iKeeper.homepage.domain.user.controller;

import com.iKeeper.homepage.domain.user.dto.request.UserRequest;
import com.iKeeper.homepage.domain.user.entity.Member;
import com.iKeeper.homepage.domain.user.service.MajorService;
import com.iKeeper.homepage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final MajorService majorService;
    private final UserService userService;

    @GetMapping(value = "/major")
    public ResponseEntity majorList() {
        return ResponseEntity.ok(majorService.searchAllMajor());
    }

    @GetMapping(value = "/users/search")
    public ResponseEntity searchUser(@RequestParam(value = "studentId") String studentId) {

        userService.searchUser(studentId);
        return ResponseEntity.ok(userService.searchUser(studentId));
    }

    @PatchMapping(value = "/users/{studentId}")
    public String updateUser(@PathVariable String studentId,
                             @RequestBody @Valid UserRequest userRequest,
                             BindingResult bindingResult, Model model) {

        userService.updateUser(studentId, userRequest, passwordEncoder);
        return "redirect:/";
    }

    @DeleteMapping(value = "/users/{studentId}")
    public ResponseEntity deleteUser(@PathVariable Member studentId) {

        userService.deleteUser(studentId);
        return ResponseEntity.ok().build();
    }
}