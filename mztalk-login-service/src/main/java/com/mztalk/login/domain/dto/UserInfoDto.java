package com.mztalk.login.domain.dto;

import lombok.*;

import java.sql.Timestamp;


@Getter
@Builder
public class UserInfoDto {

    private String username;
    private String nickname;
    private String email;
    private String role;
    private String provider;
    private String providerId;
    private Timestamp createDate;
    private String status;
    private String reportCount;


}
