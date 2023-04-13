package com.mztalk.loginservice.user.application.login.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ServiceChangeNewPasswordRequestDto {

    private Long id;
    private String prePassword;
    private String newPassword;

    @Builder
    public ServiceChangeNewPasswordRequestDto(Long id, String prePassword, String newPassword) {
        this.id = id;
        this.prePassword = prePassword;
        this.newPassword = newPassword;
    }
}
