package com.mztalk.loginservice.user.application.login.dto.response;

import com.mztalk.loginservice.user.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceMaliciousUserResponseDto {

    private String userNo;
    private String username;
    private String nickname;
    private String createDate;
    private String reportCount;

    public ServiceMaliciousUserResponseDto(User user){
        this.userNo = String.valueOf(user.getId());
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.createDate = String.valueOf(user.getCreateDate());
        this.reportCount = String.valueOf(user.getReportCount());
    }

}
