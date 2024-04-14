package com.iKeeper.homepage.domain.user.controller;

import com.iKeeper.homepage.domain.user.dto.request.UserRequest;
import com.iKeeper.homepage.domain.user.entity.User;
import com.iKeeper.homepage.domain.user.service.MajorService;
import com.iKeeper.homepage.domain.user.service.UserService;
import com.iKeeper.homepage.global.entity.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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
    public ResponseEntity searchUser(@RequestParam(value = "student") String student) {

        userService.searchUser(student);
        return ResponseEntity.ok(userService.searchUser(student));
    }

    @PatchMapping(value = "/users/{student}")
    public String updateUser(@PathVariable String student,
                             @RequestBody @Valid UserRequest userRequest,
                             BindingResult bindingResult, Model model) {

        userService.updateUser(student, userRequest, passwordEncoder);
        return "redirect:/";
    }

    @DeleteMapping(value = "/users/{student}")
    public ResponseEntity deleteUser(@PathVariable User student) {

        userService.deleteUser(student);
        return ResponseEntity.ok().build();
    }
}