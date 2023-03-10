package com.mztalk.loginservice.user.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientChangeNewEmailRequestDto {

    private long userNo;
    private String email;
}
