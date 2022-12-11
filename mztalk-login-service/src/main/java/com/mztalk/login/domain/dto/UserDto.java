package com.mztalk.login.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String username;
    private String nickname;
    private String email;
    private String role;
    private String provider;
    private String providerId;
    private Timestamp createDate;
    private String mentorStatus;
    private String status;
    private String nicknameCheck;

}
