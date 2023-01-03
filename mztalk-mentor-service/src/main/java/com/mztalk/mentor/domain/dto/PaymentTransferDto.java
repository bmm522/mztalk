package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransferDto {
    private Long id;
    private int price;
    private String impUid;
    private String merchantUid;
    private Status status;

}
