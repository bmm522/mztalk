package com.mztalk.loginservice.user.application.login.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ServiceChangeNewNicknameRequestDto {

    private long id;

    private String nickname;

    @Builder
    public ServiceChangeNewNicknameRequestDto(long userNo, String nickname) {
        this.id = userNo;
        this.nickname = nickname;
    }
}
