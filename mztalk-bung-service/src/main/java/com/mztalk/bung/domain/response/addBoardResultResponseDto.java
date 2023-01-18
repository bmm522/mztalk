package com.mztalk.bung.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class addBoardResultResponseDto {

    private Long result;

    public addBoardResultResponseDto(Long result) {
        this.result = result;
    }
}
