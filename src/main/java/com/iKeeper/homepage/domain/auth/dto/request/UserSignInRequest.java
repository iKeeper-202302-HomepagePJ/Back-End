package com.iKeeper.homepage.domain.auth.dto.request;

import lombok.Data;
@Data
public class UserSignInRequest {

    private String student;
    private String password;
}