package com.mztalk.story.domain.serviceinfo.dto;


import com.mztalk.story.status.AuctionStatus;
import com.mztalk.story.status.BungStatus;
import com.mztalk.story.status.MentorsStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceInfoRequestDto {

    private Long id;

    private MentorsStatus mentorsStatus;

    private BungStatus bungStatus;

    private AuctionStatus auctionStatus;

    private Long own;




}
