package com.mztalk.loginservice.user.application.login.dto.response;

import lombok.Builder;

import java.util.List;

public class ServiceUserInfoResponseDtos {

    List<ServiceUserInfoResponseDto> users;

    @Builder
    public ServiceUserInfoResponseDtos(List<ServiceUserInfoResponseDto> users) {
        this.users = users;
    }
}
