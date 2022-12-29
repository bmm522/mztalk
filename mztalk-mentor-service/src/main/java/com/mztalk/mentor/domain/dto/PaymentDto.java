package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Payment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Long id;
    private int price;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public Payment toEntity(){
        Payment payment = Payment.builder()
                .id(id)
                .price(price)
                .status(Status.YES)
                .build();
        return payment;
    }

    public PaymentDto(Payment payment){
        this.id = payment.getId();
        this.price = payment.getPrice();
        this.status = payment.getStatus();
        this.createdDate = payment.getCreatedDate();
        this.lastModifiedDate = payment.getLastModifiedDate();
    }

}
