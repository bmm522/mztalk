package com.mztalk.login.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeNewEmailRequestDto {

    private String userNo;
    private String email;
}
