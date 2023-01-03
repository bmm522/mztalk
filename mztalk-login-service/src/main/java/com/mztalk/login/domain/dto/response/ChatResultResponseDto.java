package com.mztalk.login.domain.dto.response;

import lombok.AllArgsConstructor;
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
