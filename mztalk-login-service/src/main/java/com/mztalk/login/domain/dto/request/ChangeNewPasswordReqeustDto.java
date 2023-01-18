package com.mztalk.login.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeNewPasswordReqeustDto {

    private String id;
    private String prePassword;

    private String newPassword;
}
