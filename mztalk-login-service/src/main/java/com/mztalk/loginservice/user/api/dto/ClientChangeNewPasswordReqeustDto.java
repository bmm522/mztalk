package com.mztalk.loginservice.user.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientChangeNewPasswordReqeustDto {

    private Long id;
    private String prePassword;

    private String newPassword;
}
