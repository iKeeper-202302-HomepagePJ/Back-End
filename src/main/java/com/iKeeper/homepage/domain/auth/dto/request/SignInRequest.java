package com.iKeeper.homepage.domain.auth.dto.request;

import com.iKeeper.homepage.global.entity.UserRole;
import lombok.Data;

@Data
public class SignInRequest {

    private Long studentId;
    private String password;
    private UserRole userRole;

    public SignInRequest(Long studentId, String password, UserRole userRole) {

        this.studentId = studentId;
        this.password = password;
        this.userRole = userRole;
    }
}