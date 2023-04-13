package com.mztalk.loginservice.chat.application;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatResultResponseDto {

    private long result;

    public ChatResultResponseDto(long result){
        this.result = result;
    }

}
