package com.mztalk.story.domain.serviceinfo.dto;


import com.mztalk.story.status.AuctionStatus;
import com.mztalk.story.status.BungStatus;
import com.mztalk.story.status.MentorsStatus;
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
