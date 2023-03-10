package com.mztalk.loginservice.global.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ClientResponseDto<T> {

    private Integer code; // 1. SUCCESS, -1 FAIL
    private String msg;
    private T body;

    @Builder
    public ClientResponseDto(int code, String msg, T body){
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

}
