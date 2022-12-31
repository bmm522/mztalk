package com.mztalk.mentor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PaymentReqDto {

    private Long userId;
    private Long boardId;
    private int price;


}
