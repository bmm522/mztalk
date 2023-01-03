package com.mztalk.mentor.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoReqDto {

    @ApiModelProperty(value="실명인증 은행고유번호", example = "004", required = true)
    private String bankCode;
    @ApiModelProperty(value="실명인증 계좌번호", example = "11011112222", required = true)
    private String bankAccount;
    @ApiModelProperty(value="실명인증 생년월일", example = "881209", required = true)
    private String birthday;

}
