package iKeeper.iKeeper.Homepage.controller;

import iKeeper.iKeeper.Homepage.model.dto.UserFormDto;
import iKeeper.iKeeper.Homepage.model.entity.User;
import iKeeper.iKeeper.Homepage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/join")
    public String userForm(@RequestBody @Valid UserFormDto userFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/asdf"; // 회원가입 페이지로 리다이렉션
        }

        try {
            User user = User.createUser(userFormDto, passwordEncoder);
            userService.saveUser(user);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/asdf"; // 회원가입 페이지로 리다이렉션
        }

        return "redirect:/"; // 회원가입 페이지로 리다이렉션
    }
}
