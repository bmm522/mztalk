package com.mztalk.login.domain.dto.response;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmailAuthResponseDto {

    private String authCode;

    public EmailAuthResponseDto(String authCode){
        this.authCode = authCode;
    }
}
