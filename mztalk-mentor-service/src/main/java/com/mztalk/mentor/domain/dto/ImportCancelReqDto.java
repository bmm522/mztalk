package com.mztalk.mentor.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ImportCancelReqDto {

    @ApiModelProperty(value="취소할 거래의 아임포트 고유번호", example = "imp_223214050206", required = true)
    private String imp_uid;
    @ApiModelProperty(value="가맹점에서 전달한 거래 고유번호", example = "ORD202313-66842459", required = true)
    private String merchant_uid;
    @ApiModelProperty(value="취소요청금액", example = "10000", required = true)
    private int price;
}
