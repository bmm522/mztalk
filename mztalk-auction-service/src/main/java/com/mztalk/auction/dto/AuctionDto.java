package com.mztalk.auction.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalTime;

@Getter
@Setter
public class AuctionDto {
    private long bId;
    private String boardTitle;
    private String boardContent;
    private Date createDate;
    private Date moidfyDate;
    private String status;
    private String boardWriter;
    private int price;
    private LocalTime timeLimit;
    private String buyerNickName;


}
