package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Payment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResDto {

    private Long id;
    private int price;
    private String impUid;
    private String merchantUid;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public PaymentResDto(Payment payment){
        this.id = payment.getId();
        this.price = payment.getPrice();
        this.impUid = payment.getImpUid();
        this.merchantUid = payment.getMerchantUid();
        this.status = payment.getStatus();
        this.createdDate = payment.getCreatedDate();
        this.lastModifiedDate = payment.getLastModifiedDate();
    }

}
