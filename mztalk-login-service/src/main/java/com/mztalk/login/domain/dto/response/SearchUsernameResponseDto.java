package com.mztalk.login.domain.dto.response;


import lombok.Getter;
import lombok.Setter;

public class SearchUsernameResponseDto {

    private String result;

    public SearchUsernameResponseDto(String result){
        this.result = result;
    }
}
