package com.mztalk.story.domain.subscribe.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.story.domain.subscribe.Subscribe;
import com.mztalk.story.status.RoleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeRequestDto {

    private Long id;

    private Long userNo;

    private int price;

    private RoleStatus roleStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime vipDate;


    public Subscribe toEntity(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
       ;

        Subscribe subscribe = Subscribe.builder()
                .id(id)
                .userNo(userNo)
                .roleStatus(RoleStatus.VIP)
                .vipDate(vipDate)
                .price(price)
                .build();

        return subscribe;


    }
}
