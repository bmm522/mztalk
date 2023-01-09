package com.mztalk.auction.domain.dto;

import com.mztalk.auction.domain.entity.Price;
import lombok.Getter;

@Getter
public class PriceDto {
    private String buyer;
    private Integer currentPrice;

    public PriceDto(Price price) {
        this.buyer = price.getBuyer();
        this.currentPrice = price.getCurrentPrice();
    }
}
