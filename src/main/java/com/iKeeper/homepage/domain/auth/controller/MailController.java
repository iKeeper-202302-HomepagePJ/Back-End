package com.iKeeper.homepage.domain.auth.controller;

import com.iKeeper.homepage.domain.auth.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/auths")
public class MailController {

    private final MailService mailService;

    @ResponseBody
    @PostMapping("/mail")
    public String MailSend(String mail) {

        int number = mailService.sendMail(mail);
        String num = "" + number;

        return num;
    }
}