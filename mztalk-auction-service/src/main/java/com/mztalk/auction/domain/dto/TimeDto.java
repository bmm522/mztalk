package com.mztalk.auction.domain.dto;

import lombok.Getter;

@Getter
public class TimeDto {
    private Long hour;
    private Long minute;
    private Long second;

    public TimeDto(Long hour, Long minute, Long second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

}
