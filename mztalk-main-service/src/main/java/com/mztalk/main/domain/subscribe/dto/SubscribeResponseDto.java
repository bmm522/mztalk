package com.mztalk.main.domain.subscribe.dto;

import com.mztalk.main.domain.subscribe.entity.Subscribe;
import com.mztalk.main.status.RoleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeResponseDto {


    private Long id;

    private Long userNo;

    private int price;

    private RoleStatus roleStatus;

    private String vipDate;

    private Timestamp createDate;

    public SubscribeResponseDto(Subscribe subscribe){
        this.id = subscribe.getId();
        this.userNo = subscribe.getUserNo();
        this.roleStatus = subscribe.getRoleStatus();
        this.vipDate = subscribe.getVipDate();
        this.createDate = subscribe.getCreateDate();
    }



}
