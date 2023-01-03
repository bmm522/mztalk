package com.mztalk.main.domain.subscribe.dto;


import com.mztalk.main.domain.subscribe.entity.Subscribe;
import com.mztalk.main.status.RoleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeRequestDto {

    private Long id;

    private Long userNo;

    private int price;

    private RoleStatus roleStatus;

    private Date vipDate;


    public Subscribe toEntity(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
       ;

        Subscribe subscribe = Subscribe.builder()
                .id(id)
                .userNo(userNo)
                .roleStatus(RoleStatus.VIP)
                .vipDate(simpleDateFormat.format(vipDate))
                .price(price)
                .build();

        return subscribe;


    }
}
