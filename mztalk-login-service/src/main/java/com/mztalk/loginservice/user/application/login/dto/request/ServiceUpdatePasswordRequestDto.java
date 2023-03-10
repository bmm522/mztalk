package com.mztalk.loginservice.user.application.login.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ServiceUpdatePasswordRequestDto {

    private String username;
    private String password;

    @Builder
    public ServiceUpdatePasswordRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
