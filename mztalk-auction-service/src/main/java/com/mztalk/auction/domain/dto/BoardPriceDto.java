package com.mztalk.auction.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPriceDto {
    private Long boardId;
    private String buyer;
    private Integer currentPrice;
}
