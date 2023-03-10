package com.mztalk.loginservice.user.application.login.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ServiceUpdateStatusRequestDto {

    private long id;

    private String status;

    @Builder
    public ServiceUpdateStatusRequestDto(long id, String status) {
        this.id = id;
        this.status = status;
    }
}
