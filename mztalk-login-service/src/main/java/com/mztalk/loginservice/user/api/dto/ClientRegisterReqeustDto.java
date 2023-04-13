package com.mztalk.loginservice.user.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClientRegisterReqeustDto {


    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;
    @NotBlank
    private String email;

}
