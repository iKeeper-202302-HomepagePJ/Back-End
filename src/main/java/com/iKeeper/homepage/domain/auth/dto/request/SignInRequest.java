package com.iKeeper.homepage.domain.auth.dto.request;

import com.iKeeper.homepage.global.entity.UserRole;
import lombok.Data;

@Data
public class SignInRequest {

    private String student;
    private String password;
    private UserRole userRole;

    public SignInRequest(String student, String password, UserRole userRole) {

        this.student = student;
        this.password = password;
        this.userRole = userRole;
    }
}