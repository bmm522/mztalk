package com.mztalk.main.domain.serviceinfo.entity;


import com.mztalk.main.status.AuctionStatus;
import com.mztalk.main.status.BungStatus;
import com.mztalk.main.status.MentorsStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
