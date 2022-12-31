package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.Payment;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PaymentReqDto {

    @ApiModelProperty(value="결제 식별자", example = "1", required = true)
    private Long userId;
    @ApiModelProperty(value="결제한 게시글의 식별자", example = "2", required = true)
    private Long boardId;
    @ApiModelProperty(value="결제 금액", example = "25000", required = true)
    private int price;

    public Payment toEntity(){
        Payment payment = Payment.builder()
                .price(price)
                .status(Status.YES)
                .build();
        return payment;
    }


}
