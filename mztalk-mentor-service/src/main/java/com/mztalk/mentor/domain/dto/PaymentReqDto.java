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
    @ApiModelProperty(value="아임포트 결제 고유번호", example = "imp-132132132", required = true)
    private String impUid;
    @ApiModelProperty(value="가맹정 주문번호", example = "ORD-656546", required = true)
    private String merchantUid;

    public Payment toEntity(){
        Payment payment = Payment.builder()
                .price(price)
                .impUid(impUid)
                .merchantUid(merchantUid)
                .status(Status.YES)
                .build();
        return payment;
    }

}
