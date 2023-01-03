package com.mztalk.bung.domain.response;

import lombok.Getter;

@Getter
public class BungListResponseDto {
    private String addNickname;
    private Long addId;

    public BungListResponseDto(String addNickname, Long addId) {
        this.addNickname = addNickname;
        this.addId = addId;
    }
}
