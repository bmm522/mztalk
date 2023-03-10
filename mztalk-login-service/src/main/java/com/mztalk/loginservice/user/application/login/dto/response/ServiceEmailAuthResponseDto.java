package com.mztalk.loginservice.user.application.login.dto.response;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ServiceEmailAuthResponseDto {

    private String authCode;

    public ServiceEmailAuthResponseDto(String authCode){
        this.authCode = authCode;
    }
}
