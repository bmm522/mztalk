package com.mztalk.loginservice.user.api.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientChangeNewNicknameRequestDto {

    private long userNo;
    private String nickname;
}
