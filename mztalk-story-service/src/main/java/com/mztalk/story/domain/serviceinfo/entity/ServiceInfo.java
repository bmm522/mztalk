package com.mztalk.story.domain.serviceinfo.entity;


import com.mztalk.story.status.AuctionStatus;
import com.mztalk.story.status.BungStatus;
import com.mztalk.story.status.MentorsStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class ServiceInfo {

    @Id
    @GeneratedValue()
    private Long id;

    private MentorsStatus mentorsStatus;

    private BungStatus bungStatus;

    private AuctionStatus auctionStatus;

    private Long own;



    public void changeMStatus(){this.mentorsStatus = MentorsStatus.YES;}

    public void changeUnMStatus(){this.mentorsStatus = MentorsStatus.NO;}

    public void changeBStatus(){this.bungStatus = BungStatus.YES;}

    public void changeUnBStatus(){this.bungStatus = BungStatus.NO;}

    public void changeAStatus(){this.auctionStatus = AuctionStatus.YES;}

    public void changeUnAStatus(){this.auctionStatus = AuctionStatus.NO;}



}
