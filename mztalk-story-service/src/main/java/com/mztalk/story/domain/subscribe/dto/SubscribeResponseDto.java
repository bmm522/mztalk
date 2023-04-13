package com.mztalk.story.domain.subscribe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.story.domain.subscribe.Subscribe;
import com.mztalk.story.status.RoleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeResponseDto {


    private Long id;

    private Long userNo;

    private int price;

    private RoleStatus roleStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime vipDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp createDate;

    public SubscribeResponseDto(Subscribe subscribe){
        this.id = subscribe.getId();
        this.userNo = subscribe.getUserNo();
        this.roleStatus = subscribe.getRoleStatus();
        this.vipDate = subscribe.getVipDate();
        this.createDate = subscribe.getCreateDate();
    }



}
