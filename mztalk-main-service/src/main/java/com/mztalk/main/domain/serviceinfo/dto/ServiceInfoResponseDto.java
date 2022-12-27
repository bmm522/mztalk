package com.mztalk.main.domain.serviceinfo.dto;


import com.mztalk.main.status.AuctionStatus;
import com.mztalk.main.status.BungStatus;
import com.mztalk.main.status.MentorsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceInfoResponseDto {

    private Long id;

    private MentorsStatus mentorsStatus;

    private BungStatus bungStatus;

    private AuctionStatus auctionStatus;

    private Long own;



}
