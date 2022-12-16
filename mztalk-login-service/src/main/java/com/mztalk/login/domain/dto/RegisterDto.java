package com.mztalk.login.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterDto {

    private String userId;
    private String password;
    private String nickname;
    private String email;


}
