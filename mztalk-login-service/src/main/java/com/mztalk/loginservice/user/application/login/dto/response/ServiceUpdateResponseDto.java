package com.mztalk.loginservice.user.application.login.dto.response;

import com.mztalk.loginservice.global.dto.Code;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ServiceUpdateResponseDto {

    private int code;

    @Builder
    public ServiceUpdateResponseDto(int code) {
        this.code = code;
    }
}
