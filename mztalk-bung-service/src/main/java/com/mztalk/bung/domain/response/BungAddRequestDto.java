package com.mztalk.bung.domain.response;

import com.mztalk.bung.domain.dto.BungAddBoardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BungAddRequestDto {
    private String message;

    public BungAddRequestDto (String message) {
        this.message = message;
    }
}
