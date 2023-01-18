package com.mztalk.main.domain.serviceinfo.dto;


import com.mztalk.main.status.AuctionStatus;
import com.mztalk.main.status.BungStatus;
import com.mztalk.main.status.MentorsStatus;
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
