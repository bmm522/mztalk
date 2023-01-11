package com.mztalk.login.domain.dto.response;


import lombok.*;



public class CheckDuplicateResponseDto {

    private String checkResult;

    public CheckDuplicateResponseDto(String checkResult){
        this.checkResult=checkResult;
    }


}
