package iKeeper.iKeeper.Homepage.controller;

import iKeeper.iKeeper.Homepage.model.dto.UserFormDto;
import iKeeper.iKeeper.Homepage.model.entity.User;
import iKeeper.iKeeper.Homepage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "")
    public String userForm(Model model) {
        model.addAttribute("userFormDto", new UserFormDto());
        return "/asdf"; //추후 프론트 경로 따라서 수정 필요
    }

    @PostMapping(value = "/join")
    public String userForm(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/asdf";
        }

        try {
            User user = User.createUser(userFormDto, passwordEncoder);
            userService.saveUser(user);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/asdf";
        }

        return "redirect:/";
    }
}
