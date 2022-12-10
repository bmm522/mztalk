package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Board;
import com.mztalk.mentor.domain.entity.Mentee;
import com.mztalk.mentor.domain.entity.Payment;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Long id;
    private Board board;
    private Mentee mentee;
    private int price;
    private Status status;

    public Payment toEntity(){
        Payment payment = Payment.builder()
                .id(id)
                .board(board)
                .mentee(mentee)
                .price(price)
                .status(Status.YES)
                .build();
        return payment;
    }

}
