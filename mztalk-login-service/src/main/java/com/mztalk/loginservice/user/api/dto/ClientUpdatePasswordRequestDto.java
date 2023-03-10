package com.mztalk.loginservice.user.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientUpdatePasswordRequestDto {

    private String username;
    private String password;
}
