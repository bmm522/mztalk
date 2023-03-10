package com.mztalk.loginservice.user.application.login.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ServiceEmailAuthRequestDto {

    private String email;
    private String username;

    @Builder
    public ServiceEmailAuthRequestDto(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
