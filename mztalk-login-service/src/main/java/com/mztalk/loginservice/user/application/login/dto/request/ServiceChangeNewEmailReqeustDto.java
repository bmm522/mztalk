package com.mztalk.loginservice.user.application.login.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ServiceChangeNewEmailReqeustDto {

    private long id;


    private String email;


    @Builder
    public ServiceChangeNewEmailReqeustDto(long id, String email) {
        this.id = id;
        this.email = email;
    }
}
