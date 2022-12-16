package com.mztalk.gateway.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "UserBuilder")
public class User {

    private String id;
    private String username;
    private String nickname;
    private String email;
    private String role;
    private String provider;
    private String providerId;
    private String createDate;
    private String mentorStatus;
    private String nicknameCheck;
}
