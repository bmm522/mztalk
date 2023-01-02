package com.mztalk.mentor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ImportCancelReqDto {

    private String imp_uid;
    private String merchant_uid;
    private int price;
}
