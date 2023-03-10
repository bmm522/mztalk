package com.mztalk.loginservice.user.application.login.dto.response;

import com.mztalk.loginservice.user.repository.entity.User;
import lombok.*;

import java.sql.Timestamp;


@Getter
public class ServiceUserInfoResponseDto {


    private String userId;
    private String username;
    private String nickname;
    private String email;
    private String role;
    private String provider;
    private String providerId;
    private Timestamp createDate;
    private String status;
    private String reportCount;

    private String imageUrl;

    @Builder
    public ServiceUserInfoResponseDto(String userId, String username, String nickname, String email, String role, String provider, String providerId, Timestamp createDate, String status, String reportCount, String imageUrl) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
        this.status = status;
        this.reportCount = reportCount;
        this.imageUrl = imageUrl;
    }


    //    public ServiceUserInfoResponseDto(User user){
//        this.userId = String.valueOf(user.getId());
//        this.username = user.getUsername();
//        this.nickname = user.getNickname();
//        this.email = user.getEmail();
//        this.role = user.getRole();
//        this.provider = user.getProvider();
//        this.providerId = user.getProviderId();
//        this.createDate = user.getCreateDate();
//        this.status = user.getStatus();
//        this.reportCount = String.valueOf(user.getReportCount());
//    }
}
